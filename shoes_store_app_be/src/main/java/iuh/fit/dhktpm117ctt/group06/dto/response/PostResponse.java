package iuh.fit.dhktpm117ctt.group06.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String avatar;
    private String createdDate;
}
