package downstagram.downstagram.controller;

import downstagram.downstagram.domain.Post;
import downstagram.downstagram.domain.PostImage;
import downstagram.downstagram.domain.PostStatus;
import downstagram.downstagram.domain.User;
import downstagram.downstagram.model.UserDto;
import downstagram.downstagram.service.FollowService;
import downstagram.downstagram.service.PostService;
import downstagram.downstagram.service.UserService;
import downstagram.downstagram.utils.CommonFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    @GetMapping("/main")
    public String main_page(@PageableDefault(size = 5) Pageable pageable, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("posts", postService.findPostPageable(pageable));
        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/views/main";
    }

    @GetMapping("/main/user/{id}")
    public String main_user(@PathVariable("id") Long id, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        User u = userService.findById(id);
        List<Post> imagePosts = postService.findImagePosts(u);

        model.addAttribute("posting", imagePosts);
        model.addAttribute("pageUser", new UserDto(userService.findById(id)));
        model.addAttribute("countPost", postService.countPost(id));
        model.addAttribute("following", followService.countFollowing(id));
        model.addAttribute("follower", followService.countFollower(id));
        model.addAttribute("follow", followService.find(id, userId));

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

    @GetMapping("/main/upload")
    public String main_upload(Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("user", new UserDto(userService.findByUserId(userId)));

        return "/views/upload";
    }

    @PostMapping("/main/posting")
    public String main_posting(HttpServletRequest request, MultipartHttpServletRequest mtfRequest, Model model) throws Exception {
        // userId로 images 폴더에 userId로 폴더만들기
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        String reqUserId = request.getParameter("userid");
        User user = userService.findByUserId(reqUserId);
        String description = request.getParameter("description");
        String location = request.getParameter("location");

        List<MultipartFile> fileList = mtfRequest.getFiles("files");
        List<PostImage> postImages = CommonFileUtils.feedUpload(fileList, userId);
        if (postImages != null) {
            Post post = Post.createPost(user, description, location, PostStatus.FREE, postImages);
            postService.save(post);
        }

        return "redirect:/main";
    }
}
