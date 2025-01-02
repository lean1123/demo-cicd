package iuh.fit.dhktpm117ctt.group06.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String id;
    private double amount;
    private String paymentDate;
    private String transactionId;
    private String content;
    private OrderResponse order;
}
