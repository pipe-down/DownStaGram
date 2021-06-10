package downstagram.downstagram.repository;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.utils.EncryptionUtils;
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
class UserRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    public void login() {
        // given
        User user = new User("testId", EncryptionUtils.encryptMD5("test"));
        em.persist(user);

        // when
        User result = userRepository.findUserByUserId(user.getUserId());

        // then
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
    }


}