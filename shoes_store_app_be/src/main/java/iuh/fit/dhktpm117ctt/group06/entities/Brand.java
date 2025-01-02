package iuh.fit.dhktpm117ctt.group06.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "brands")
public class Brand implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String brandName;
    private String avatar;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductCollection> productCollections;
}
