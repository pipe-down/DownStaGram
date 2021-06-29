package downstagram.downstagram;

import downstagram.downstagram.domain.*;
import downstagram.downstagram.repository.CommentRepository;
import downstagram.downstagram.repository.UserRepository;
import downstagram.downstagram.service.PostService;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
        initService.init2();
        initService.init3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final UserService userService;
        private final PostService postService;
        private final CommentRepository commentRepository;

        public void init() {
            for (int i = 0; i < 10; i++) {
                User user = User.createUser("test"+i, "test"+i, "테스트"+i, "자기소개~!"+i, "01011112222", "https://github.com/pipe-down");
                if(i%2==0) {
                    user.enableUser(1);
                }
                user.updateUser(user.getName(), user.getWebsite(), user.getIntroduce());
                em.persist(user);
            }
        }

        public void init2() {
            for (int i = 1; i < 9; i++) {
                List<User> users = userService.list();
                Follow follow = Follow.createFollow(users.get(i), users.get(0));
                em.persist(follow);
            }
        }

        public void init3() {
            User user = userService.findByUserId("test1");
            List<PostImage> postImages = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                PostImage postImage = new PostImage("testPath"+i+".gif");
                postImages.add(postImage);
            }
            Post post = Post.createPost(user, "descriptionTest", "locationTEst", PostStatus.FREE, postImages);

            postService.save(post);
            Comment comment = Comment.createComment(user, post, "commentTest");
            commentRepository.save(comment);

        }
    }
}