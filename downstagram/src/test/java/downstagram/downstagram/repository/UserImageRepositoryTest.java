package downstagram.downstagram.repository;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.domain.UserImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class UserImageRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserImageRepository userImageRepository;

    @Test
    public void createUserImage() {
        User user1 = userRepository.findUserByUserId("test1@naver.com");
        User user2 = userRepository.findUserByUserId("test2@naver.com");

        UserImage userImage = UserImage.createUserImage("TEST", user1);
        UserImage userImage2 = UserImage.createUserImage("TEST2", user2);
        em.persist(userImage);
        em.persist(userImage2);

        assertThat(userImage.getUser().getId()).isEqualTo(user1.getId());
        assertThat(userImage2.getUser().getId()).isEqualTo(user2.getId());
    }

}