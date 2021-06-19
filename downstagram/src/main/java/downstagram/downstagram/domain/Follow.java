package downstagram.downstagram.domain;

import downstagram.downstagram.utils.EncryptionUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity(name = "indi_follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "follow_uk",
                        columnNames = {"following", "follower"}
                )
        }
)
public class Follow extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "following")
    @ManyToOne(fetch = LAZY)
    private User following;

    @JoinColumn(name = "follower")
    @ManyToOne(fetch = LAZY)
    private User follower;

    public Follow(User following, User follower) {
        this.following = following;
        this.follower = follower;
    }

    public static Follow createFollow(User following, User follower) {
        return new Follow(following, follower);
    }
}
