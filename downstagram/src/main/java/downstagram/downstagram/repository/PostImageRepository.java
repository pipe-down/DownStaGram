package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    List<PostImage> findByPost(Post post);
}
