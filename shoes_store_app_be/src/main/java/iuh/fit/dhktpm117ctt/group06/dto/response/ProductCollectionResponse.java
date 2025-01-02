package iuh.fit.dhktpm117ctt.group06.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCollectionResponse {
    private String id;
    private String name;
    private String brandId;
}
