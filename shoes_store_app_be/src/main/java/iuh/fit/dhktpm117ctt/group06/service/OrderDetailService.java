package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.OrderDetailRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.OrderDetailResponse;
import iuh.fit.dhktpm117ctt.group06.entities.OrderDetail;
import iuh.fit.dhktpm117ctt.group06.exception.AppException;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetailResponse> findByOrder(String orderId);
    Optional<OrderDetailResponse> addToOrder(String orderId,OrderDetailRequest orderDetailRequest);
	OrderDetailResponse updateQuantity(String id, OrderDetailRequest orderDetailRequest) throws AppException;
}
