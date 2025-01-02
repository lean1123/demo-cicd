package iuh.fit.dhktpm117ctt.group06.dto.request;

import iuh.fit.dhktpm117ctt.group06.exception.ErrorCode;
import iuh.fit.dhktpm117ctt.group06.util.ValidationConstraints;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
//@Builder
@NoArgsConstructor
public class SignUpRequest {
    @Email(message = "EMAIL_INVALID")
    @NotBlank(message = "EMAIL_INVALID")
    @NotNull(message = "EMAIL_INVALID")
    private String email;
    @NotBlank(message = "PASSWORD_INVALID")
    @NotNull(message = "PASSWORD_INVALID")
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    @NotBlank(message = "FIRSTNAME_INVALID")
    @NotNull(message = "FIRSTNAME_INVALID")
    @Size(min = 1, max = 50, message = "FIRSTNAME_INVALID")
    @Pattern(regexp = ValidationConstraints.VIETNAMESE_NAME_REGEX, message = "FIRSTNAME_INVALID")
    private String firstName;
    @NotBlank(message = "LASTNAME_INVALID")
    @NotNull(message = "LASTNAME_INVALID")
    @Size(min = 1, max = 50, message = "LASTNAME_INVALID")
    @Pattern(regexp = ValidationConstraints.VIETNAMESE_NAME_REGEX, message = "LASTNAME_INVALID")
    private String lastName;
}
