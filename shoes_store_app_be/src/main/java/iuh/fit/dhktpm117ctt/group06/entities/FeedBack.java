package iuh.fit.dhktpm117ctt.group06.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "feedbacks")
public class FeedBack implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String content;
    private float rating;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "feedback_list_detail_images",
            joinColumns = {
                    @JoinColumn(name = "feedback_id")
            }
    )
    private List<String> listDetailImages;
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
