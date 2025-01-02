package iuh.fit.dhktpm117ctt.group06.dto.request;

import iuh.fit.dhktpm117ctt.group06.entities.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentRequest {
    private double amount;
    private LocalDateTime paymentDate;
    private String transactionId;
    private String content;
    private String orderId;
}
