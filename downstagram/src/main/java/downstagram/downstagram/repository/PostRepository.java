package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"user"}) //내부적으로 fetch join
    List<Post> findAll();

    List<Post> findByUser(Long id);

    List<Post> findByUserOrderByIdDesc(User user);
}
