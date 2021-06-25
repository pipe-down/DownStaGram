package downstagram.downstagram.repository;

import downstagram.downstagram.domain.Follow;
import downstagram.downstagram.domain.TableStatus;
import downstagram.downstagram.domain.User;
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

        long count1 = followRepository.countByFollowingIdAndEnable(1L, TableStatus.Y);
        long count2 = followRepository.countByFollowerIdAndEnable(2L, TableStatus.Y);
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }

    @Test
    public void unfollow() {
        followRepository.deleteByFollowingIdAndFollowerId(1L, 2L);
        long count = followRepository.countByFollowingIdAndEnable(1L, TableStatus.Y);
        assertThat(count).isEqualTo(7);
    }

    @Test
    public void checkEnable() {
        User user1 = userService.findById(1L);
        User user2 = userService.findById(2L);

        user1.enableUser(1);
        Follow follow = Follow.createFollow(user2, user1);
        followRepository.save(follow);
        Follow follow1 = followRepository.findByFollowerIdAndFollowingUserId(user1.getId(), user2.getUserId()).orElse(null);

        assertThat(follow1.getEnable()).isEqualTo(TableStatus.N);
    }


    
}