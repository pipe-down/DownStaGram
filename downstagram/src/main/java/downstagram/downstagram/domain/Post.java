package downstagram.downstagram.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "indi_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indi_post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "indi_user_id")
    private User user;

    private String description;
    private String location;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Heart> hearts = new ArrayList<>();

    public static Post createPost(User user, String description, String location, PostStatus postStatus, List<PostImage> postImages) {
        Post post = new Post(description, location, postStatus);
        post.setUser(user);
        for (PostImage postImage : postImages) {
            post.addPostImage(postImage);
        }
        return post;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void addPostImage(PostImage postImage) {
        postImages.add(postImage);
        postImage.setPost(this);
    }

    public Post(String description, String location, PostStatus postStatus) {
        this.description = description;
        this.location = location;
        this.postStatus = postStatus;
    }

}
