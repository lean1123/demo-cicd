package iuh.fit.dhktpm117ctt.group06.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedBackResponse {
    private String id;
    private String content;
    private float rating;
    private String[] listDetailImages;
    private String createdDate;
}
