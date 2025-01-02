package iuh.fit.dhktpm117ctt.group06.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
}
