package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    long countByUserId(Long id);

    List<Post> findByUser(Long id);

    List<Post> findByUserOrderByIdDesc(User user);

    @EntityGraph(attributePaths = {"user"}) //내부적으로 fetch join
    Page<Post> findAll(Pageable pageable);
}
