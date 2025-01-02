package iuh.fit.dhktpm117ctt.group06.dto.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductGender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double rating;
    private LocalDateTime createdDate;
    private ProductCollectionResponse collection;
    private CategoryResponse category;
    private ProductGender gender;
    private List<ProductColor> colors;
    private List<String> sizes;
}
