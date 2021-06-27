package downstagram.downstagram.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

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

    @Enumerated(EnumType.STRING)
    private TableStatus enable;

    public Follow(User following, User follower, TableStatus enable) {
        this.following = following;
        this.follower = follower;
        this.enable = enable;
    }

    public void acceptFollow() {
        this.enable = TableStatus.Y;
    }

    public static Follow createFollow(User following, User follower) {
        if (follower.getEnable() == TableStatus.Y) {
            return new Follow(following, follower, TableStatus.N);
        } else {
            return new Follow(following, follower, TableStatus.Y);
        }
    }
}
