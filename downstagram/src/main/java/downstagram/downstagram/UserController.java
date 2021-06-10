package downstagram.downstagram;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> findUsers() {
        return userService.list();
    }

    @GetMapping("/users/{userId}/{password}")
    public UserDto findUser(@PathVariable("userId") String userId, @PathVariable("password") String password) {
        User user = userService.login(userId, password);
        return new UserDto(user.getUserId());
    }

}
