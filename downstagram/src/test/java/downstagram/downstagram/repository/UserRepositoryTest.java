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

import java.util.List;

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

    @Test
    public void register() {
        User user = User.createUser("TEST_REGI", "ST_pasword", "testname", null, "phoneNumber", null);
        userRepository.save(user);

        em.flush();
        em.clear();

        List<User> result = userRepository.findByUserId("TEST_REGI");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).extracting("name").containsExactly("testname");
    }

    @Test
    public void update() {
        User user = User.createUser("TEST_REGI", "ST_pasword", "testname", null, "phoneNumber", null);
        userRepository.save(user);

        em.flush();
        em.clear();

        User findUser = userRepository.findUserByUserId("TEST_REGI");
        findUser.updateUser("TEST001", "TEST002", "TEST003");
        assertThat(findUser.getName()).isEqualTo("TEST001");
        assertThat(findUser.getWebsite()).isEqualTo("TEST002");
        assertThat(findUser.getIntroduce()).isEqualTo("TEST003");
    }

}