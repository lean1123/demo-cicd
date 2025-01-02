package iuh.fit.dhktpm117ctt.group06.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCollectionRequest {
    @NotNull(message = "PRODUCT_COLLECTION_NAME")
    @NotBlank(message = "PRODUCT_COLLECTION_NAME")
    private String name;
    @NotNull
    private String brandId;
}
