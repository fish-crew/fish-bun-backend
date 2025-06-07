package fish.member.book.rating.controller;

import fish.member.book.rating.dto.BookRatingRequest;
import fish.member.book.rating.service.BookRatingService;
import fish.domain.user.index.User;
import fish.common.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/book-rating")
@Tag(name = "붕어빵 도감 API")
public class BookRatingController {
    private final BookRatingService bookRatingService;

    @Operation(summary = "사용자별 붕어빵 도감 평점 수정")
    @PatchMapping("{flavorId}")
    public ResponseEntity<?> modify(@PathVariable Long flavorId, @AuthenticationPrincipal User user,
                     @RequestBody BookRatingRequest request) {
        bookRatingService.modify(flavorId, user.getId(), request.getRating());
        return ResponseEntity.ok(ResponseUtil.success());
    }
}