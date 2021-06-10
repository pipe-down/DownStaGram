package downstagram.downstagram.repository;

import downstagram.downstagram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUserId(String userId);

    User findByUserId(String userId);
}
