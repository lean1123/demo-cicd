package iuh.fit.dhktpm117ctt.group06.entities;

import iuh.fit.dhktpm117ctt.group06.entities.enums.OrderStatus;
import iuh.fit.dhktpm117ctt.group06.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "orders")
public class Order implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double totalPrice;
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    @ToString.Exclude
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
