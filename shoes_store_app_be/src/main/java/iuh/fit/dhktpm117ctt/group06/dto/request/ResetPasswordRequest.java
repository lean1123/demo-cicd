package iuh.fit.dhktpm117ctt.group06.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
