package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.OrderDetailRequest;
import iuh.fit.dhktpm117ctt.group06.dto.request.OrderRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.OrderDetailResponse;
import iuh.fit.dhktpm117ctt.group06.dto.response.OrderResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Account;
import iuh.fit.dhktpm117ctt.group06.entities.Address;
import iuh.fit.dhktpm117ctt.group06.entities.Order;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.entities.enums.OrderStatus;
import iuh.fit.dhktpm117ctt.group06.exception.AppException;
import iuh.fit.dhktpm117ctt.group06.exception.ErrorCode;
import iuh.fit.dhktpm117ctt.group06.repository.AccountRepository;
import iuh.fit.dhktpm117ctt.group06.repository.AddressRepository;
import iuh.fit.dhktpm117ctt.group06.repository.OrderRepository;
import iuh.fit.dhktpm117ctt.group06.repository.UserRepository;
import iuh.fit.dhktpm117ctt.group06.service.MailSenderService;
import iuh.fit.dhktpm117ctt.group06.service.OrderDetailService;
import iuh.fit.dhktpm117ctt.group06.service.OrderService;
import iuh.fit.dhktpm117ctt.group06.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    private final ModelMapper modelMapper = new ModelMapper();

//	@Autowired
//	public OrderServiceImpl(OrderRepository orderRepository) {
//		this.orderRepository = orderRepository;
//	}

    private OrderResponse mapToOrderResponse(Order order) {
        return modelMapper.map(order, OrderResponse.class);
    }

    private Order mapToOrder(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, Order.class);
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderResponse> findById(String id) {
        return orderRepository.findById(id).map(this::mapToOrderResponse);
    }

    @Override
    public List<OrderResponse> findByUser(String userId) {
        return orderRepository.findByUserId(userId).stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderResponse> updateStatus(String id, OrderRequest orderRequest) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return Optional.empty();
        }

        Order order = optionalOrder.get();
        order.setOrderStatus(orderRequest.getOrderStatus());

        Order updatedOrder = orderRepository.save(order);
        return Optional.of(mapToOrderResponse(updatedOrder));
    }

    @Override
    public Optional<OrderResponse> updateQuantity(String id, OrderDetailRequest orderDetailRequest) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return Optional.empty();
        }

        Order order = optionalOrder.get();

        try {
            orderDetailService.updateQuantity(orderDetailRequest.getId(), orderDetailRequest);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Optional.empty();
        }

        List<OrderDetailResponse> orderResponses = orderDetailService.findByOrder(id);
        double totalPrice = 0;
        for (OrderDetailResponse orderDetailResponse : orderResponses) {
            totalPrice += orderDetailResponse.getPricePerItem() * orderDetailResponse.getQuantity();
        }
        order.setTotalPrice(totalPrice);

        Order updatedOrder = orderRepository.save(order);
        return Optional.of(mapToOrderResponse(updatedOrder));
    }

    @Override
    public List<OrderResponse> searchOrders(String keyword) {
        return orderRepository.search(keyword).stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

//	@Override
//	public Optional<OrderResponse> saveOrder(OrderRequest orderRequest) {
//		Optional<User> user = userRepository.findById(orderRequest.getUserId());
//		Optional<Address> addresOptional = addressRepository.findById(orderRequest.getAddressId());
//
//		if (user.isEmpty()) {
//			throw new AppException(ErrorCode.USER_NOT_FOUND);
//		}
//
//		if (addresOptional.isEmpty()) {
//			throw new AppException(ErrorCode.ADDRESS_NOT_FOUND);
//		}
//
//		User userEntity = user.get();
//
//		Order order = new Order();
//
//		BeanUtils.copyProperties(orderRequest, order);
//
//		order.setUser(user.get());
//		order.setAddress(addresOptional.get());
//		order.setOrderStatus(OrderStatus.PENDING);
//		order.setPaymentMethod(orderRequest.getPaymentMethod());
//		order.setCreatedDate(LocalDateTime.now());
//		order.setTotalPrice(calculateTotalPrice(orderRequest.getOrderDetails()));
//
//		Order savedOrder = orderRepository.save(order);
//
//
//
//		List<OrderDetailRequest> detailRequests = orderRequest.getOrderDetails();
//
//		List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
//
//		for (OrderDetailRequest orderDetailRequest : detailRequests) {
//			orderDetailResponses.add(orderDetailService.addToOrder(savedOrder.getId(), orderDetailRequest).get());
//		}
//
//		Optional<OrderResponse> orderResponseOptional = Optional.of(mapToOrderResponse(savedOrder));
//
//		if(savedOrder.getId() != null){
//			Optional<Account> account = accountRepository.findByUser(userEntity.getId());
//
////			String itemDetais = "Your products: \n"
////					+ orderDetailResponses.stream().map(orderDetailResponse -> orderDetailResponse.getProductItem().getProduct().getName() + " - " + orderDetailResponse.getQuantity() + " - " + orderDetailResponse.getPricePerItem() + "\n").collect(Collectors.joining());
//
//            account.ifPresent(value -> {
//				Map<String, Object> messageObject = new LinkedHashMap<>();
//				messageObject.put("receiver", value.getEmail());
//				messageObject.put("type", "ordering");
//				messageObject.put("content", "Hi! Your order has been placed successfully. \n");
//
//
//				jmsTemplate.convertAndSend("email_queue", messageObject);
//			});
//		}
//
//		return orderResponseOptional;
//	}

    @Override
    public Optional<OrderResponse> saveOrder(OrderRequest orderRequest) {
        Optional<User> user = userRepository.findById(orderRequest.getUserId());
        Optional<Address> addressOptional = addressRepository.findById(orderRequest.getAddressId());

        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        if (addressOptional.isEmpty()) {
            throw new AppException(ErrorCode.ADDRESS_NOT_FOUND);
        }

        User userEntity = user.get();

        // Tạo đơn hàng
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        order.setUser(user.get());
        order.setAddress(addressOptional.get());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setCreatedDate(LocalDateTime.now());
        order.setTotalPrice(calculateTotalPrice(orderRequest.getOrderDetails()));

        Order savedOrder = orderRepository.save(order);


        List<OrderDetailRequest> detailRequests = orderRequest.getOrderDetails();
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();

        for (OrderDetailRequest orderDetailRequest : detailRequests) {
            orderDetailResponses.add(orderDetailService.addToOrder(savedOrder.getId(), orderDetailRequest).get());
        }

        orderDetailResponses.stream().forEach(orderDetailResponse -> {
            System.out.println("Product name: " + orderDetailResponse.getProductItem().getProduct().getName());
        });

        Optional<OrderResponse> orderResponseOptional = Optional.of(mapToOrderResponse(savedOrder));


        if (savedOrder.getId() != null) {
            Optional<Account> account = accountRepository.findByUser(userEntity.getId());
            account.ifPresent(value -> {

                String itemDetails = orderDetailResponses.stream()
                        .map(orderDetailResponse -> {
                            System.out.println("Product name: " + orderDetailResponse.getProductItem().getProduct().getName());
                            return String.format(

                                    "%s - Quantity: %d - Price: %.2f",
                                    orderDetailResponse.getProductItem().getProduct().getName(),
                                    orderDetailResponse.getQuantity(),
                                    orderDetailResponse.getPricePerItem()

                            );
                        })
                        .collect(Collectors.joining("\n"));


                String emailContent = String.format(
                        "Hi %s! Your order has been placed successfully.\n\nOrder Details:\n%s\n\nTotal Price: %.2f",
                        userEntity.getFirstName() + " " + userEntity.getLastName(),
                        itemDetails,
                        savedOrder.getTotalPrice()
                );


                Map<String, Object> messageObject = new LinkedHashMap<>();
                messageObject.put("receiver", value.getEmail());
                messageObject.put("type", "ordering");
                messageObject.put("content", emailContent);

                jmsTemplate.convertAndSend("email_queue", messageObject);
            });
        }

        return orderResponseOptional;
    }


    private double calculateTotalPrice(List<OrderDetailRequest> orderDetails) {
        double totalPrice = 0;
        for (OrderDetailRequest orderDetail : orderDetails) {
            totalPrice += orderDetail.getPricePerItem() * orderDetail.getQuantity();
        }
        return totalPrice;
    }
}
