package downstagram.downstagram.controller;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
}
