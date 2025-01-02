package iuh.fit.dhktpm117ctt.group06.entities;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDetailPK implements Serializable{
    /**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	private String cartId;
    private String productItemId;
}
