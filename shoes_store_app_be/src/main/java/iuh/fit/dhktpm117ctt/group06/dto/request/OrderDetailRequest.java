package iuh.fit.dhktpm117ctt.group06.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderDetailRequest {
	@Nullable
	private String id;
	@NotNull(message = "Product item id must not be null")
    private String productItemId;
	@NotNull(message = "Quantity must not be null")
    private int quantity;
	@NotNull(message = "Price per item must not be null")
    private double pricePerItem;
    @Nullable
    private String orderId;
}
