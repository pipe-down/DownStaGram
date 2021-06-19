package downstagram.downstagram.service;

import downstagram.downstagram.domain.Follow;
import downstagram.downstagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

    public void save(Long login_id, Long page_id) { // 팔로우
        Follow follow = Follow.createFollow(userService.findById(login_id), userService.findById(page_id));
        followRepository.save(follow);
    }

    @Transactional
    public void cancelFollow(Long following, Long follower) {
        followRepository.deleteByFollowingIdAndFollowerId(following, follower);
    }

    public boolean find(Long id, String userId) {
        return followRepository.countByFollowerIdAndFollowingUserId(id, userId) == 0 ? false : true;
    }

    public long countFollowing(Long id) {
        return followRepository.countByFollowingId(id);
    }

    public long countFollower(Long id) {
        return followRepository.countByFollowerId(id);
    }
}
