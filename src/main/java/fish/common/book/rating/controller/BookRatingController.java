package fish.common.book.rating.controller;

import fish.common.book.rating.dto.request.BookRatingRequest;
import fish.common.book.rating.service.BookRatingService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/book-rating")
@Tag(name = "붕어빵 도감 API")
public class BookRatingController {
    private final BookRatingService bookRatingService;

    @Operation(summary = "사용자별 붕어빵 도감 평점 조회")
    @PatchMapping("{flavorId}")
    public ResponseEntity<?> modify(@PathVariable Long flavorId, @AuthenticationPrincipal User user,
                     @RequestBody BookRatingRequest request) {
        bookRatingService.modify(flavorId, user.getId(), request.getRating());
        return ResponseEntity.ok(ResponseUtil.success());
    }
}