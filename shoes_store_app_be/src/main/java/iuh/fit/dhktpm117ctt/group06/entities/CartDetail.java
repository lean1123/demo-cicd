package iuh.fit.dhktpm117ctt.group06.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cart_details")
public class CartDetail implements Serializable {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartDetailPK cartDetailPK;

	private int quantity;

	@OneToOne
	@JoinColumn(name = "product_item_id")
	@MapsId("productItemId")
	private ProductItem productItem;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	@MapsId("cartId")
	@JsonIgnore
	private Cart cart;
}
