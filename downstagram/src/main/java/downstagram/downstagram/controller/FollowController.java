package downstagram.downstagram.controller;

import downstagram.downstagram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public String follow(HttpServletRequest request, Model model) throws Exception {
        String l = request.getParameter("userId");
        String p = request.getParameter("pageId");

        Long login_id = Long.parseLong(l);
        Long page_id = Long.parseLong(p);

        followService.save(login_id, page_id);

        String redirect_url = "redirect:/main/user/" + page_id;

        return redirect_url;
    }

    @PostMapping("/unfollow")
    public String unfollow(HttpServletRequest request, Model model) throws Exception {
        String l = request.getParameter("userId");
        String p = request.getParameter("pageId");

        Long login_id = Long.parseLong(l);
        Long page_id = Long.parseLong(p);

        followService.cancelFollow(login_id, page_id);
        String redirect_url = "redirect:/main/user/" + page_id;

        return redirect_url;
    }
}
