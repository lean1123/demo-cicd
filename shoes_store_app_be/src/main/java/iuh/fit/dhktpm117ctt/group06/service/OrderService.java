package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.OrderDetailRequest;
import iuh.fit.dhktpm117ctt.group06.dto.request.OrderRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderResponse> findAll();
    Optional<OrderResponse> findById(String id);
    List<OrderResponse> findByUser(String userId);
    Optional<OrderResponse> updateStatus(String id, OrderRequest orderRequest);
	Optional<OrderResponse> saveOrder(OrderRequest orderRequest);
	Optional<OrderResponse> updateQuantity(String id, OrderDetailRequest orderRequest);
    List<OrderResponse> searchOrders(String keyword);
}
