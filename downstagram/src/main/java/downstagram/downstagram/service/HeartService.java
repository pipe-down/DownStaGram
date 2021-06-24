package downstagram.downstagram.service;

import downstagram.downstagram.domain.Heart;
import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartService {

    private final HeartRepository heartRepository;

    public int checkHeart(Long heartId, Long postId) {
        return heartRepository.countByUserIdAndPostId(heartId, postId);
    }

    public Heart findHeart(Long id) {
        return heartRepository.findFirstByPostId(id).orElse(null);
    }

    public long total_cnt(Long postId) {
        return heartRepository.countByPostId(postId);
    }

    @Transactional
    public void save(User heartUser, Post post) {
        Heart heart = Heart.createHeart(heartUser, post);
        heartRepository.save(heart);
    }

    @Transactional
    public void delete(Long heartId, Long postId) {
        heartRepository.deleteByUserIdAndPostId(heartId, postId);
    }
}
