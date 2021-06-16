package downstagram.downstagram.controller;

import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.UserService;
import downstagram.downstagram.utils.CommonFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @RequestMapping(value = "/main/user/image_insert")
    public String image_insert(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile, Model model) throws Exception {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserId(userId);
        String redirect_url = "redirect:/main/user/update/" + user.getId(); // 사진업로드 이후 redirect될 url

        String profileName = CommonFileUtils.profileUpload(mFile, user);

        if (profileName != null) {
            userService.profile_image(userId, profileName); // 프로필 사진이름 db에 update
        }

        return redirect_url;
    }
}
