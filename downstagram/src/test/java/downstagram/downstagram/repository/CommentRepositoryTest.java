package downstagram.downstagram.repository;

import downstagram.downstagram.domain.*;
import downstagram.downstagram.service.PostService;
import downstagram.downstagram.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @Test
    public void commentSave() {
        User user = userService.findByUserId("test1");
        List<PostImage> postImages = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            PostImage postImage = new PostImage("testPath"+i);
            postImages.add(postImage);
        }
        Post post = Post.createPost(user, "descriptionTest", "locationTEst", PostStatus.FREE, postImages);

        postService.save(post);
        Comment comment = Comment.createComment(user, post, "commentTest");
        commentRepository.save(comment);

        List<Comment> result = commentRepository.findByPostId(post.getId());

        assertThat(result.get(0).getContent()).isEqualTo("commentTest");
    }

}