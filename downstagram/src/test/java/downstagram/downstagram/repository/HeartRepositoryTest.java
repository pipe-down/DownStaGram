package downstagram.downstagram.repository;

import downstagram.downstagram.domain.*;
import downstagram.downstagram.service.PostService;
import downstagram.downstagram.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class HeartRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserService userService;
    
    @Autowired
    PostRepository postRepository;
    
    @Autowired
    HeartRepository heartRepository;

    @Test
    public void insertHeart() {
        User user = userService.findById(2L);

        List<PostImage> postImages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            PostImage postImage = new PostImage("testPath"+i);
            postImages.add(postImage);
        }


        Post post = Post.createPost(user, "descriptionTest", "locationTEst", PostStatus.FREE, postImages);
        postRepository.save(post);

        List<Post> posts = postRepository.findAll();
        User user2 = userService.findById(3L);

        for (Post post1 : posts) {
            Heart heart = Heart.createHeart(user2, post1);
            heartRepository.save(heart);
        }

        int count = heartRepository.countByUserIdAndPostId(user2.getId(), post.getId());

        assertThat(count).isEqualTo(1);

    }

    @Test
    public void deleteHeart() {
        User user = userService.findById(2L);

        List<PostImage> postImages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            PostImage postImage = new PostImage("testPath"+i);
            postImages.add(postImage);
        }


        Post post = Post.createPost(user, "descriptionTest", "locationTEst", PostStatus.FREE, postImages);
        postRepository.save(post);

        List<Post> posts = postRepository.findAll();
        User user2 = userService.findById(3L);

        for (Post post1 : posts) {
            Heart heart = Heart.createHeart(user2, post1);
            heartRepository.save(heart);
        }

        heartRepository.deleteByUserIdAndPostId(user2.getId(), post.getId());

        int count = heartRepository.countByUserIdAndPostId(user2.getId(), post.getId());

        assertThat(count).isEqualTo(0);
    }

}