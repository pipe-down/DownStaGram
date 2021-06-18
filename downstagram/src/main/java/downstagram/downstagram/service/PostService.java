package downstagram.downstagram.service;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.repository.PostImageRepository;
import downstagram.downstagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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

    public List<Post> findPosts(User user) {
        return postRepository.findByUserOrderByIdDesc(user);
    }

    public List<Post> findImagePosts(User user) {
        return postRepository.findByUserOrderByIdDesc(user);
    }

}
