package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select f from indi_comment f join fetch f.user join fetch f.post where f.post.id = :pid")
    List<Comment> findByPostId(@Param("pid") Long id);

}
