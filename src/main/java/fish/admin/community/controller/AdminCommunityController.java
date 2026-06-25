package fish.admin.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.common.community.post.entity.PostEntity;
import fish.common.community.post.dto.request.PostRequest;
import fish.common.community.post.dto.response.PostDetailResponse;
import fish.common.community.post.dto.response.PostResponse;
import fish.common.community.post.service.PostService;
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
@RequestMapping(value = "/admin/community")
@Tag(name = "[관리자] 기능 API")
public class AdminCommunityController {
    private final PostService postService;

    @GetMapping("/post/list")
    public String viewPostList() {
        return "main/community/post/list";
    }

    @Operation(summary = "[관리자] 커뮤니티 게시글 리스트 조회")
    @GetMapping(value = "/post/list.json")
    @ResponseBody
    public Map<String, List<PostResponse>> listPost() {
        Map<String, List<PostResponse>> result = new HashMap<>();
        result.put("data", postService.findPostList());

        return result;
    }

    @GetMapping(value = "/post/detail/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) throws JsonProcessingException {
        PostDetailResponse postDetailResponse = postService.findPost(postId);
        model.addAttribute("post", postDetailResponse);

        return "main/community/post/detail";
    }

    @GetMapping(value = "/post/save")
    public String viewPostSave() {
        return "main/community/post/save";
    }

    @PostMapping(value = "/post/save.json", consumes = {"multipart/form-data"})
    public String savePost(@ModelAttribute PostRequest request) throws IOException {
        PostEntity post = PostEntity.toEntity(request);
        Long postId = postService.savePost(post, request.getPictures());

        return "redirect:/admin/community/post/detail/" + postId;
    }
  
    // For Redirect Using Get Method
    @ResponseStatus(HttpStatus.SEE_OTHER)
    @DeleteMapping(value = "/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return "redirect:/admin/community/post/list";
    }

    @GetMapping(value = "/edit/{postId}")
    public String viewEditPost(@PathVariable Long postId, Model model) throws IOException {
        PostDetailResponse postDetailResponse = postService.findPost(postId);
        model.addAttribute("post", postDetailResponse);

        return "main/community/post/edit";
    }

    @PostMapping(value = "/edit/{postId}", consumes = {"multipart/form-data"})
    public String modifyPost(@PathVariable Long postId, @ModelAttribute PostRequest request) throws IOException {
        postService.updatePost(postId, request, request.getPictures());

        return "redirect:/admin/community/post/detail/" + postId;
    }
}
