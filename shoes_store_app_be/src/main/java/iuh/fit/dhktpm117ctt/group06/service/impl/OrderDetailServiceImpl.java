package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.OrderDetailRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.OrderDetailResponse;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductItemResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Order;
import iuh.fit.dhktpm117ctt.group06.entities.OrderDetail;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.exception.AppException;
import iuh.fit.dhktpm117ctt.group06.exception.ErrorCode;
import iuh.fit.dhktpm117ctt.group06.repository.OrderDetailRepository;
import iuh.fit.dhktpm117ctt.group06.repository.OrderRepository;
import iuh.fit.dhktpm117ctt.group06.repository.ProductItemRepository;
import iuh.fit.dhktpm117ctt.group06.repository.ProductRepository;
import iuh.fit.dhktpm117ctt.group06.service.OrderDetailService;
import iuh.fit.dhktpm117ctt.group06.service.ProductItemService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductItemRepository productItemRepository;

	@Autowired
	private ProductItemService productItemService;

	private ModelMapper modelMapper = new ModelMapper();

//    @Autowired
//    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
//        this.orderDetailRepository = orderDetailRepository;
//        this.orderRepository = orderRepository;
//    }

	private OrderDetailResponse mapToOrderDetailResponse(OrderDetail orderDetail) {
		return modelMapper.map(orderDetail, OrderDetailResponse.class);
	}

	private OrderDetail mapToOrderDetail(OrderDetailRequest orderDetailRequest) {
		return modelMapper.map(orderDetailRequest, OrderDetail.class);
	}

	private OrderDetailResponse mapToOrderDetailResponse1(OrderDetail orderDetail) {
		OrderDetailResponse orderDetailResponse = modelMapper.map(orderDetail, OrderDetailResponse.class);
		ProductItemResponse productItemResponse = modelMapper.map(orderDetail.getProductItem(), ProductItemResponse.class);
		orderDetailResponse.setProductItem(productItemResponse);
		return orderDetailResponse;
	}

	@Override
	public List<OrderDetailResponse> findByOrder(String orderId) {
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
		return orderDetails.stream().map(this::mapToOrderDetailResponse1).collect(Collectors.toList());
	}

	@Override
	public Optional<OrderDetailResponse> addToOrder(String orderId, OrderDetailRequest orderDetailRequest) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isEmpty()) {
			return Optional.empty();
		}
		Order order = optionalOrder.get();

		OrderDetail orderDetail = mapToOrderDetail(orderDetailRequest);
		orderDetail.setOrder(order);

		OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);

		ProductItem optional = productItemRepository.findById(orderDetail.getProductItem().getId())
				.orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ITEM_NOT_FOUND));

		int currentQuantity = optional.getQuantity();
		
		
		if (orderDetailRequest.getQuantity() >= 0 && orderDetailRequest.getQuantity() <= currentQuantity) {
			currentQuantity -= orderDetailRequest.getQuantity();
			productItemService.updateQuantity(optional.getId(), currentQuantity);
		} else {
			throw new AppException(ErrorCode.QTY_INVALID);
		}

		return Optional.of(mapToOrderDetailResponse(savedOrderDetail));
	}

	@Override
	public OrderDetailResponse updateQuantity(String id, OrderDetailRequest orderDetailRequest) throws AppException{
		OrderDetail orderDetail = orderDetailRepository.findById(orderDetailRequest.getId())
				.orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

		int orderDetailQuantity = orderDetail.getQuantity();

		ProductItem productItemOptional = productItemRepository.findById(orderDetailRequest.getProductItemId())
				.orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ITEM_NOT_FOUND));

		if (orderDetailRequest.getQuantity() < 0 || orderDetailRequest.getQuantity() > productItemOptional.getQuantity()) {
			throw new AppException(ErrorCode.PRODUCT_ITEM_NOT_ENOUGH);
		}

		orderDetail.setQuantity(orderDetailRequest.getQuantity());

		orderDetailRepository.save(orderDetail);

		if (orderDetailQuantity < orderDetailRequest.getQuantity()) {
			int currentQuantity = productItemOptional.getQuantity();
			currentQuantity -= orderDetailRequest.getQuantity() - orderDetailQuantity;
			productItemService.updateQuantity(productItemOptional.getId(), currentQuantity);
		} else {
			int currentQuantity = productItemOptional.getQuantity();
			currentQuantity += orderDetailQuantity - orderDetailRequest.getQuantity();
			productItemService.updateQuantity(productItemOptional.getId(), currentQuantity);
		}

		return mapToOrderDetailResponse(orderDetail);
	}
}
