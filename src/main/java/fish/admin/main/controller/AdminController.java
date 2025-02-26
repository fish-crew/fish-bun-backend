package fish.admin.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.common.community.post.entity.PostEntity;
import fish.common.community.post.dto.request.PostRequest;
import fish.common.community.post.dto.response.PostDetailResponse;
import fish.common.community.post.dto.response.PostResponse;
import fish.common.community.post.service.PostService;
import fish.common.detail.service.DetailService;
import fish.common.flavor.service.FlavorService;
import fish.common.main.service.MainService;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
    private final FlavorService flavorService;
    private final UserService userService;
    private final PostService postService;
    private final DetailService detailService;

    @GetMapping(value = "/report/list")
    public String viewReport() {
        return "main/report/list";
    }

    @GetMapping(value = "/report/list.json")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> listReport() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("data", flavorService.findAllReports());
        return result;
    }

    @GetMapping("/user/list")
    public String viewUser() {
        return "main/user/list";
    }

    @GetMapping(value = "/user/list.json")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> listUser() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("data", detailService.findAllUserStats());
        return result;
    }

    @GetMapping(value="/user/detail")
    public String viewUserDetail(@RequestParam Long userId, Model model) {
        model.addAttribute("data", detailService.findUserStats(userId));
        return "main/user/detail";
    }

    @GetMapping("/community/post/list")
    public String viewPostList() {
        return "main/community/post/list";
    }

    @GetMapping(value = "/community/post/list.json")
    @ResponseBody
    public Map<String, List<PostResponse>> listPost() {
        Map<String, List<PostResponse>> result = new HashMap<>();
        result.put("data", postService.findPostList());

        return result;
    }

    @GetMapping(value = "/community/post/detail/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) throws JsonProcessingException {
        PostDetailResponse postDetailResponse = postService.findPost(postId);
        model.addAttribute("post", postDetailResponse);

        return "main/community/post/detail";
    }

    @GetMapping(value = "community/post/save")
    public String viewPostSave() {
        return "main/community/post/save";
    }

    @PostMapping(value = "community/post/save.json", consumes = {"multipart/form-data"})
    public String savePost(@ModelAttribute PostRequest request) throws IOException {
        PostEntity post = PostEntity.toEntity(request);
        Long postId = postService.savePost(post, request.getPictures());

        return "redirect:/admin/community/post/detail/" + postId;
    }

    /**
     * 유저별 일지 데이터를 볼 수 있는 기능
     * @param model
     * @return String
     */
}
