package iuh.fit.dhktpm117ctt.group06.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddressResponse {
    private String id;
    private String homeNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
}
