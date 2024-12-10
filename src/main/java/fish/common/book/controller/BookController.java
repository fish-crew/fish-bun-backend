package fish.common.book.controller;

import fish.common.book.response.UserBookResponse;
import fish.common.book.service.BookService;
import fish.common.response.FishBunResponse;
import fish.core.oauth.dto.AuthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/book")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/user")
    public ResponseEntity<FishBunResponse<List<UserBookResponse>>> getUserFishBunBookList(@AuthenticationPrincipal AuthUserInfo principal) {
        List<UserBookResponse> data = bookService.findAllUserFishBunBook(principal.getUser().getId());
        return ResponseEntity.ok(new FishBunResponse<>(data));
    }
}
