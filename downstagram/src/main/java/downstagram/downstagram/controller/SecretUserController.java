package downstagram.downstagram.controller;

import downstagram.downstagram.domain.TableStatus;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.FollowService;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SecretUserController {

    private final UserService userService;
    private final FollowService followService;

    @GetMapping("/secret/view")
    @ResponseBody
    private boolean secret_user(Model model, Long id) {

        return userService.enableCheck(id);
    }

    @GetMapping("/secret/follow/list")
    public String reqFollow(Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findByUserId(userId);

        List<User> users = followService.followingReq(u.getId(), TableStatus.N);
        model.addAttribute("user", new UserDto(u));
        model.addAttribute("fUser", users.stream().map(UserDto::new).collect(Collectors.toList()));

        return "/views/secretList";
    }

    @PostMapping("/secret/accept/{id}")
    @ResponseBody
    private int acceptFollow(@PathVariable Long id) { // 비공개
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findByUserId(userId);
        followService.acceptFollow(u.getId(),id);

        return 1;
    }

    @PostMapping("/secret/insert/{id}")
    @ResponseBody
    private int secret_enable(@PathVariable Long id) { // 비공개
        userService.enable_user(id, 1);

        return 1;
    }

    @PostMapping("/secret/delete/{id}")
    @ResponseBody
    private int secret_disable(@PathVariable Long id) { // 공개
        userService.enable_user(id, 0);

        return 1;
    }
}
