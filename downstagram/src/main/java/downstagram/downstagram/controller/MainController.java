package downstagram.downstagram.controller;

import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/main")
    public String main_page(Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("userList", userService.list());
        model.addAttribute("user", userService.findByUserId(userId));

        return "/views/main";
    }

    @GetMapping("/main/user/{id}")
    public String main_user(@PathVariable("id") Long id, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUserId(userId));

        return "/views/main";
    }
}
