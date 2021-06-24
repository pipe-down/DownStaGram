package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {


    @Query("select count(h) from indi_heart h where h.user.id = :heartId and h.post.id = :postId ")
    int countByUserIdAndPostId(@Param("heartId") Long heartId, @Param("postId") Long postId);

    long countByPostId(Long id);

    @Modifying
    int deleteByUserIdAndPostId(Long heartId, Long postId);

    Optional<Heart> findFirstByPostId(Long id);
}
