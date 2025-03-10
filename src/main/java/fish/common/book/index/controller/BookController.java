package fish.common.book.index.controller;

import fish.common.book.index.dto.response.UserBookDetailResponse;
import fish.common.book.index.dto.response.UserBookResponse;
import fish.common.book.index.service.BookService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/book")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/user")
    public ResponseEntity<ResponseUtil<List<UserBookResponse>>> getUserFishBunBookList
            (@AuthenticationPrincipal User user) {
        List<UserBookResponse> data = bookService.findAllUserFishBunBook(user.getId());
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @GetMapping(value = "/detail/{flavorId}")
    public ResponseEntity<?> getDetail(@AuthenticationPrincipal User user, @PathVariable("flavorId") Long flavorId) {
        UserBookDetailResponse response = bookService.findUserBookDetail(user.getId(), flavorId);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }
}