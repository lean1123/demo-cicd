package iuh.fit.dhktpm117ctt.group06.entities;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart implements Serializable {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
