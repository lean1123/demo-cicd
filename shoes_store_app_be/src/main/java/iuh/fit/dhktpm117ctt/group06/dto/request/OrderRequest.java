package iuh.fit.dhktpm117ctt.group06.dto.request;

import iuh.fit.dhktpm117ctt.group06.entities.enums.OrderStatus;
import iuh.fit.dhktpm117ctt.group06.entities.enums.PaymentMethod;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class OrderRequest {
	@NotNull(message = "Total price must not be null")
    private double totalPrice;
    @Nullable
    private LocalDateTime createdDate;
    @Nullable
    private OrderStatus orderStatus;
    @NotNull(message = "Payment method must not be null")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Order details must not be null")
    private List<OrderDetailRequest> orderDetails;
    @NotNull(message = "User id must not be null")
    private String userId;
    @NotNull(message = "Address id must not be null")
    private String addressId;
    
}
