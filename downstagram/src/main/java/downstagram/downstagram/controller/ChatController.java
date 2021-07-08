package downstagram.downstagram.controller;

import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final UserService userService;

    @GetMapping("/chat/room")
    public String chat(Model model){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/chat/chat";
    }
}
