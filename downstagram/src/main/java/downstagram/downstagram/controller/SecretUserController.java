package downstagram.downstagram.controller;

import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SecretUserController {

    private final UserService userService;

    @GetMapping("/secret/view")
    @ResponseBody
    private boolean secret_user(Model model, Long id) {

        return userService.enableCheck(id);
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
