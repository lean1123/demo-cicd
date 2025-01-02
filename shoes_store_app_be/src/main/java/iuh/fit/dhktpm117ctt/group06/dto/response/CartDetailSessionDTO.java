package iuh.fit.dhktpm117ctt.group06.dto.response;

import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CartDetailSessionDTO {
    private ProductItem productItem;
    private int quantity;
}
