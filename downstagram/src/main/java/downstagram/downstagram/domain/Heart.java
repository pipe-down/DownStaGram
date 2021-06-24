package downstagram.downstagram.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "indi_heart")
@NoArgsConstructor
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "heart_uk",
                        columnNames = {"heartId", "postId"}
                )
        }
)
public class Heart extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "heartId", nullable = false)
    @ManyToOne(fetch = LAZY)
    private User user;

    @JoinColumn(name = "postId", nullable = false)
    @ManyToOne(fetch = LAZY)
    private Post post;

    public Heart(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public static Heart createHeart(User heartId, Post postId) {
        return new Heart(heartId, postId);
    }
}
