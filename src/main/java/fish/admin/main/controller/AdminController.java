package fish.admin.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.user.community.post.entity.PostEntity;
import fish.user.community.post.dto.request.PostRequest;
import fish.user.community.post.dto.response.PostDetailResponse;
import fish.user.community.post.dto.response.PostResponse;
import fish.user.community.post.service.PostService;
import fish.user.flavor.service.FlavorService;
import fish.user.user.entity.User;
import fish.user.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "[관리자] 기능 API")
public class AdminController {
    private final FlavorService flavorService;
    private final UserService userService;
    private final PostService postService;

    @GetMapping(value = "/report/list")
    public String viewReport() {
        return "main/report/list";
    }

    @Operation(summary = "[관리자] 제보된 붕어빵 조회")
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

    @Operation(summary = "[관리자] 유저 리스트 조회")
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

    @Operation(summary = "[관리자] 커뮤니티 게시글 리스트 조회")
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
  
    @GetMapping(value = "test/ws")
    public String viewTestWS() {
        return "main/ws/test";
    }
}
