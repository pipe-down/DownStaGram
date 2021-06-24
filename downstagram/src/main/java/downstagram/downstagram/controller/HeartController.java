package downstagram.downstagram.controller;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.repository.PostRepository;
import downstagram.downstagram.service.HeartService;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HeartController {

    private final UserService userService;
    private final HeartService heartService;
    private final PostRepository postRepository;

    @GetMapping("/like/view")
    @ResponseBody // Json전달가능
    private Map<String, Object> like_view(Model model, Long id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findByUserId(userId);

        int cnt = heartService.checkHeart(u.getId(), id); // 1 : 누름, 0 : 안누름
        long total_cnt = heartService.total_cnt(id);

        String userName = new String();
        if (total_cnt > 0) {
            userName = heartService.findHeart(id).getUser().getName();
        }


        // Map으로 만들어준다음 return
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("cnt", cnt);
        m.put("total_cnt", total_cnt);
        m.put("userName", userName);

        return m;
    }

    @PostMapping("/like/insert/{id}")
    @ResponseBody
    private int like_insert(@PathVariable Long id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserId(userId);
        Post post = postRepository.findById(id).orElse(null);

        heartService.save(user, post);
        return 1;
    }

    @PostMapping("/like/delete/{id}")
    @ResponseBody
    private int like_delete(@PathVariable Long id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findByUserId(userId);

        heartService.delete(u.getId(), id);
        return 1;
    }
}
