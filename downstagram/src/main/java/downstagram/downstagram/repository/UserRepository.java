package downstagram.downstagram.repository;

import downstagram.downstagram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUserId(String userId);
    List<User> findByUserId(String UserId);
}
