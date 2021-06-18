package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.PostImage;
import downstagram.downstagram.domain.PostStatus;
import downstagram.downstagram.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class PostRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void posting() {
        User user = userRepository.findUserByUserId("test1");

        List<PostImage> postImages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            PostImage postImage = new PostImage("testPath"+i);
            postImages.add(postImage);
        }


        Post post = Post.createPost(user, "descriptionTest", "locationTEst", PostStatus.FREE, postImages);
        postRepository.save(post);


        List<Post> result = postRepository.findAll();

        assertThat(result).extracting("user").containsExactly(user);
        assertThat(result).extracting("postStatus").containsExactly(PostStatus.FREE);

    }

}