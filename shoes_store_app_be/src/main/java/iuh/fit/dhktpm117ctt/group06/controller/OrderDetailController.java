package iuh.fit.dhktpm117ctt.group06.controller;

import iuh.fit.dhktpm117ctt.group06.dto.response.OrderDetailResponse;
import iuh.fit.dhktpm117ctt.group06.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;

	@GetMapping("/order/{orderId}")
	public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable String orderId) {
		Map<String, Object> response = new LinkedHashMap<>();

		List<OrderDetailResponse> orderDetails = orderDetailService.findByOrder(orderId);

		if (orderDetails.isEmpty()) {
			response.put("status", HttpStatus.NOT_FOUND);
			response.put("data", "Order detail not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		response.put("status", HttpStatus.OK);
		response.put("data", orderDetails);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
