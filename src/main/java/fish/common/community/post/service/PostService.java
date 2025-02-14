package fish.common.community.post.service;

import fish.common.community.post.entity.Post;
import fish.common.community.post.repository.PostRepository;
import fish.common.community.post.response.PostResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    public List<PostResponse> findPostList() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostResponse::toResponse).toList();
    }

    public PostResponse findPost(Long postId) {
        return PostResponse.toResponse(postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post Entity not founded with Id: " + postId)));
    }

    public Long savePost(Post post) {
        return postRepository.save(post).getId();
    }
}
