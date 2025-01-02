package iuh.fit.dhktpm117ctt.group06.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {
    @NotNull(message = "BRAND_NAME_INVALID")
    @NotBlank(message = "BRAND_NAME_INVALID")
    private String brandName;
    private MultipartFile avatar;
}
