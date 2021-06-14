package downstagram.downstagram.controller;

import downstagram.downstagram.model.UserRegistrationModel;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class GuestController {
    private final UserService userService;

    @GetMapping({"/","/guest/login"})
    public String login(Model model) {
        return "/guest/login";
    }

    @GetMapping("guest/register")
    public String register(Model model) {
        model.addAttribute("userModel", new UserRegistrationModel());
        return "/guest/registerPage";
    }

    @PostMapping("guest/register")
    public String registerSet(@ModelAttribute("userModel") @Valid UserRegistrationModel userModel, BindingResult result) {
        if (result.hasErrors()) {
            return "guest/registerPage"; // 에러 발생하면 다시 가입창
        }

        userService.save(userModel);

        return "redirect:/";
    }
}
