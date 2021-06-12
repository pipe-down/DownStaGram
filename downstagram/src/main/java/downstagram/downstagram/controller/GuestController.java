package downstagram.downstagram.controller;

import downstagram.downstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
    @Autowired
    UserService userService;

    @GetMapping({"/","/guest/login"})
    public String login(Model model) throws Exception {
        return "/guest/login";
    }
}
