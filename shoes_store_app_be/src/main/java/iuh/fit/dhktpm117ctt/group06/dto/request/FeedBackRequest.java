package iuh.fit.dhktpm117ctt.group06.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class FeedBackRequest {
    private String content;
    private float rating;
    private MultipartFile[] listDetailImages;
    private Date createdDate;
    private String orderId;
    private String userId;
    private String productId;
}
