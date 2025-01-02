package iuh.fit.dhktpm117ctt.group06.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "product_items")
public class ProductItem implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double price;
    private int quantity;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "product_item_detail_images",
            joinColumns = @JoinColumn(name = "product_item_id")
    )
    private List<String> listDetailImages;
    @Enumerated(EnumType.STRING)
    private ProductColor color;
    private String size;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
