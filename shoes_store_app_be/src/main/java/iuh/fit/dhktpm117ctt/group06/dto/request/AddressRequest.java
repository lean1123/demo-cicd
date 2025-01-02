package iuh.fit.dhktpm117ctt.group06.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotNull(message = "HOME_NUMBER_INVALID")
    @NotBlank(message = "HOME_NUMBER_INVALID")
    private String homeNumber;
    @NotNull(message = "STREET_INVALID")
    @NotBlank(message = "STREET_INVALID")
    private String street;
    @NotNull(message = "WARD_INVALID")
    @NotBlank(message = "WARD_INVALID")
    private String ward;
    @NotNull(message = "DISTRICT_INVALID")
    @NotBlank(message = "DISTRICT_INVALID")
    private String district;
    @NotNull(message = "CITY_INVALID")
    @NotBlank(message = "CITY_INVALID")
    private String city;
}
