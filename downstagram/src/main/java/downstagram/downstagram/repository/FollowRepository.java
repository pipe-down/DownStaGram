package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Follow;
import downstagram.downstagram.domain.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    long countByFollowerIdAndEnable(Long id, TableStatus enable);
    long countByFollowingIdAndEnable(Long id, TableStatus enable);

    long countByFollowerIdAndFollowingUserId(Long id, String userId); // 팔로우 되어있는지 count하는 메서드

    Optional<Follow> findByFollowerIdAndFollowingUserId(Long id, String userId);

    @Modifying
    @Transactional
    void deleteByFollowingIdAndFollowerId(Long id1, Long id2); // 언팔로우 메서드

}
