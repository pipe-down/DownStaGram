package downstagram.downstagram.service;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.repository.PostImageRepository;
import downstagram.downstagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

    public Page<Post> findPostPageable(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findImagePosts(User user) {
        return postRepository.findByUserOrderByIdDesc(user);
    }

    public long countPost(Long id) {
        return postRepository.countByUserId(id);
    }

}
