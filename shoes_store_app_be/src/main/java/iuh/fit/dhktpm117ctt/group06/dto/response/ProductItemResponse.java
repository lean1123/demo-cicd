package iuh.fit.dhktpm117ctt.group06.dto.response;


import java.util.List;
import iuh.fit.dhktpm117ctt.group06.entities.Product;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemResponse {
    private String id;
    private double price;
    private int quantity;
    private List<String> listDetailImages;
    private String color;
    private String size;
    private ProductResponse product;
    private String status;
}
