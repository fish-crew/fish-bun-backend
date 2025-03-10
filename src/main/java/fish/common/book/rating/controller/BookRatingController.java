package fish.common.book.rating.controller;

import fish.common.book.rating.dto.request.BookRatingRequest;
import fish.common.book.rating.service.BookRatingService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/book-rating")
public class BookRatingController {
    private final BookRatingService bookRatingService;

    @PatchMapping("{flavorId}")
    public ResponseEntity<?> modify(@PathVariable Long flavorId, @AuthenticationPrincipal User user,
                     @RequestBody BookRatingRequest request) {
        bookRatingService.modify(flavorId, user.getId(), request.getRating());
        return ResponseEntity.ok(ResponseUtil.success());
    }
}