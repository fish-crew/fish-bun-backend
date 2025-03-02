package fish.admin.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.common.community.post.entity.PostEntity;
import fish.common.community.post.dto.request.PostRequest;
import fish.common.community.post.dto.response.PostDetailResponse;
import fish.common.community.post.dto.response.PostResponse;
import fish.common.community.post.service.PostService;
import fish.common.flavor.service.FlavorService;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public Map<String, List<User>> listUser() {
        Map<String, List<User>> result = new HashMap<>();
        result.put("data", userService.findAllUsers());
        return result;
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

    // For Redirect Using Get Method
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @DeleteMapping(value = "community/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return "redirect:/admin/community/post/list";
    }

    @GetMapping(value = "community/edit/{postId}")
    public String viewEditPost(@PathVariable Long postId, Model model) throws IOException {
        PostDetailResponse postDetailResponse = postService.findPost(postId);
        model.addAttribute("post", postDetailResponse);

        return "main/community/post/edit";
    }

    @PostMapping(value = "community/edit/{postId}", consumes = {"multipart/form-data"})
    public String modifyPost(@PathVariable Long postId, @ModelAttribute PostRequest request) throws IOException {
        postService.updatePost(postId, request, request.getPictures());

        return "redirect:/admin/community/post/detail/" + postId;
    }
}
