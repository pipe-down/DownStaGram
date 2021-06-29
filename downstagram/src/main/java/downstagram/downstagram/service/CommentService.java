package downstagram.downstagram.service;

import downstagram.downstagram.domain.Comment;
import downstagram.downstagram.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<Comment> findByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }

    @Transactional
    public int save(Long uId, Long pId, String cmt) {
        Comment comment = Comment.createComment(userService.findById(uId), postService.findPost(pId), cmt);
        commentRepository.save(comment);
        return 1;
    }

    @Transactional
    public int deleteById(Long id) {
        commentRepository.deleteById(id);
        return 1;
    }
}
