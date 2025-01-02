package iuh.fit.dhktpm117ctt.group06.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
public class PostRequest {
    @NotNull(message = "TITLE_INVALID")
    @NotBlank(message = "TITLE_INVALID")
    private String title;
    @NotNull(message = "CONTENT_INVALID")
    @NotBlank(message = "CONTENT_INVALID")
    private String content;
    private MultipartFile avatar;
    private Date createdDate;
}
