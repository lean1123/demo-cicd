package iuh.fit.dhktpm117ctt.group06.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "payment")
public class Payment implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double amount;
    private Date paymentDate;
    private String transactionId;
    private String content;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
