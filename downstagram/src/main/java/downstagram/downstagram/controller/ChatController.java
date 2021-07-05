package downstagram.downstagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat/room")
    public String chat(){
        return "/chat/chat";
    }
}
