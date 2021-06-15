package downstagram.downstagram.controller;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/main")
    public String main_page(Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("userList", userService.list());
        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/views/main";
    }

    @GetMapping("/main/user/{id}")
    public String main_user(@PathVariable("id") Long id, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/views/myPage";
    }

    @GetMapping("/main/user/update/{id}")
    public String update_user(@PathVariable("id") Long id, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/views/update";
    }

    @PostMapping("/main/user/info_update")
    public String profile_update(HttpServletRequest request, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserId(userId);
        String redirect_url = "redirect:/main/user/" + user.getId();

        String name = request.getParameter("name");
        String website = request.getParameter("website");
        String introduce = request.getParameter("intro");

        userService.profile_update(userId, name, website, introduce);
        return redirect_url;
    }
}
