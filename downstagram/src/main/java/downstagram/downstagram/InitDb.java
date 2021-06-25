package downstagram.downstagram;

import downstagram.downstagram.domain.Follow;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
        initService.init2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final UserRepository userRepository;

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
                List<User> users = userRepository.findAll();
                Follow follow = Follow.createFollow(users.get(0), users.get(i));
                em.persist(follow);
            }
        }
    }
}