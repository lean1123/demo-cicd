package iuh.fit.dhktpm117ctt.group06.dto.request;

import iuh.fit.dhktpm117ctt.group06.entities.ProductCollection;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import iuh.fit.dhktpm117ctt.group06.entities.Brand;
import iuh.fit.dhktpm117ctt.group06.entities.Category;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductSize;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class ProductRequest {
    @NotNull(message = "PRODUCT_NAME_INVALID")
    @NotBlank(message = "PRODUCT_NAME_INVALID")
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @NotNull(message = "COLLECTION_ID_INVALID")
    private ProductCollection collection;
    @NotNull(message = "CATEGORY_ID_INVALID")
    private Category category;
    @NotNull(message = "PRODUCT_GENDER_INVALID")
    private ProductGender gender;
    private double rating;
}
