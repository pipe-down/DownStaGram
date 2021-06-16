package downstagram.downstagram;

import downstagram.downstagram.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void init() {
            for (int i = 0; i < 10; i++) {
                User user = User.createUser("test"+i, "test"+i, "테스트"+i, "자기소개~!"+i, "01011112222", "https://github.com/pipe-down");
                user.updateUser(user.getName(), user.getWebsite(), user.getIntroduce());
                em.persist(user);
            }
        }
    }
}