package downstagram.downstagram.controller;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.UserService;
import downstagram.downstagram.utils.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    private List<UserDto> findUsers() {
        return userService.list().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/{password}")
    private UserDto findUser(@PathVariable("userId") String userId, @PathVariable("password") String password) {
        User user = userService.login(userId, password);
        return new UserDto(user.getUserId());
    }

    @GetMapping("/main/user/delete")
    private String main_upload(Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/views/delete";
    }

    @PostMapping("/user/delete")
    private String deleteUser(@RequestParam(value = "password") String password) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findByUserId(userId);
        if(u.getPassword().equals(EncryptionUtils.encryptMD5(password))) {
            userService.delete(u.getId());
            return "redirect:/";
        }

        return "redirect:/main/user/delete";
    }

}
