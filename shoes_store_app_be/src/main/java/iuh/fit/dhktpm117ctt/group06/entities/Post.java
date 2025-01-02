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
@Table(name = "posts")
public class Post implements Serializable{
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    @Column(columnDefinition = "longtext")
    private String content;
    private String author;
    private String avatar;
    private String createdDate;
}
