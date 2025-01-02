package iuh.fit.dhktpm117ctt.group06.dto.response;

import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderDetailResponse {
    private String id;
    @Nullable
    private String orderId;
    private int quantity;
    private double pricePerItem;
//    private ProductItemResponse productItem;
    private ProductItemResponse productItem;
}
