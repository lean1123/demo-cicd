package iuh.fit.dhktpm117ctt.group06.entities;


import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
