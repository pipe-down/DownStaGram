package downstagram.downstagram.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "indi_user_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indi_user_image_id")
    private Long id;

    private String imagePath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "indi_user_id")
    private User user;

    // 연관관계 메서드
    public void setUserImage(User user) {
        this.user = user;
        user.getUserImages().add(this);
    }

    public static UserImage createUserImage(String imagePath, User user) {
        UserImage userImage = new UserImage(imagePath);
        userImage.setUserImage(user);
        return userImage;
    }

    public UserImage(String imagePath) {
        this.imagePath = imagePath;
    }
}
