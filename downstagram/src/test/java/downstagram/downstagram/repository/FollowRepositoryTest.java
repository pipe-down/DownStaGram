package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Follow;
import downstagram.downstagram.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class FollowRepositoryTest {

    @Autowired
    UserService userService;

    @Autowired
    FollowRepository followRepository;
    
    @Test 
    public void follow() {
        Follow follow = Follow.createFollow(userService.findById(1L), userService.findById(2L));
        followRepository.save(follow);

        long count = followRepository.countByFollowerIdAndFollowingUserId(2L, "test0");
        assertThat(count).isEqualTo(1);

        long count1 = followRepository.countByFollowingId(1L);
        long count2 = followRepository.countByFollowerId(2L);
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }

    @Test
    public void unfollow() {
        followRepository.deleteByFollowingIdAndFollowerId(1L, 2L);
        long count = followRepository.countByFollowingId(1L);
        assertThat(count).isEqualTo(7);
    }

    
}