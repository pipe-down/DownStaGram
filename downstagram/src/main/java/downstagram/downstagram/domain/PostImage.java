package downstagram.downstagram.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "indi_post_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indi_user_image_id")
    private Long id;

    private String imagePath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "indi_post_id")
    private Post post;

    public PostImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "PostImage{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
