package downstagram.downstagram.repository;

import downstagram.downstagram.domain.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {

}
