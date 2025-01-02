package iuh.fit.dhktpm117ctt.group06.dto.response;

import iuh.fit.dhktpm117ctt.group06.entities.enums.OrderStatus;
import iuh.fit.dhktpm117ctt.group06.entities.enums.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderResponse {
    private String id;
    private double totalPrice;
    private LocalDateTime createdDate;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private List<OrderDetailResponse> orderDetails;
    private UserResponse user;
    private AddressResponse address;
}
