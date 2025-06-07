package fish.member.book.index.controller;

import fish.member.book.index.dto.BookDetailResponse;
import fish.member.book.index.dto.BookResponse;
import fish.member.book.index.service.BookService;
import fish.domain.user.index.User;
import fish.common.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/member/book")
@Tag(name = "붕어빵 도감 API")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "사용자용 붕어빵 도감 조회")
    @GetMapping(value = "/user")
    public ResponseEntity<ResponseUtil<List<BookResponse>>> getUserFishBunBookList
            (@AuthenticationPrincipal User user) {
        List<BookResponse> data = bookService.findAllUserFishBunBook(user.getId());
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @Operation(summary = "사용자용 붕어빵 도감 상세 조회")
    @Parameter(name = "flavorId", description = "붕어빵 고유 ID")
    @GetMapping(value = "/detail/{flavorId}")
    public ResponseEntity<?> getDetail(@AuthenticationPrincipal User user, @PathVariable("flavorId") Long flavorId) {
        BookDetailResponse response = bookService.findUserBookDetail(user.getId(), flavorId);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }
}