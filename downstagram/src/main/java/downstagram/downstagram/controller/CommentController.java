package downstagram.downstagram.controller;

import downstagram.downstagram.domain.Comment;
import downstagram.downstagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/list")
    @ResponseBody
    private List<Comment> comment_list(Model model, Long id) {
        return commentService.findByPostId(id);
    }

    @PostMapping("/comment/insert")
    @ResponseBody
    private int comment_insert(Long uid, Long pid, String content) {
        return commentService.save(uid, pid, content);
    }

    @PostMapping("/comment/delete/{id}")
    @ResponseBody
    private int comment_delete(@PathVariable Long id) {
        return commentService.deleteById(id);
    }

}
