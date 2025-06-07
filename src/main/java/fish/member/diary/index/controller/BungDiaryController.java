package fish.member.diary.index.controller;

import fish.member.book.index.service.BookService;
import fish.member.diary.index.dto.BungDiaryFlavor;
import fish.member.diary.index.dto.BungDiaryRequest;
import fish.domain.diary.index.BungDiary;
import fish.member.diary.index.dto.BungDiaryResponse;
import fish.member.diary.index.service.BungDiaryService;
import fish.domain.user.index.User;
import fish.common.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member/detail")
@Tag(name = "붕어빵 등록 API")
public class BungDiaryController {
    private final BungDiaryService bungDiaryService;
    private final BookService bookService;

    @Operation(summary = "붕어빵 일지 등록", description = "먹은 붕어빵 종류 및 개수와 사진을 등록하는 API")
    @PostMapping(value = "/save", consumes = {"multipart/form-data"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUtil<Long>> save(@ModelAttribute BungDiaryRequest request,
                                                   @AuthenticationPrincipal User user) throws IOException {
        BungDiary entity = request.toEntity(request, user.getId());
        Long id = bungDiaryService.save(entity, request.getPicture());
        // Update the user book for a new flavor
        List<Long> flavorIdList = entity.getFlavors().stream().map(BungDiaryFlavor::getFlavorId).toList();
        bookService.saveUserCompletedFlavor(flavorIdList, user.getId());

        return ResponseEntity.ok(ResponseUtil.success(id));
    }

    @Operation(summary = "등록 완료 데이터 조회", description = "등록 완료 붕어빵 icon code 반환 API")
    @GetMapping(value = "/save-success/{detailId}")
    public ResponseEntity<ResponseUtil<List<BungDiaryResponse>>> findRegistrationData(@PathVariable Long detailId,
                                                                                      @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(ResponseUtil.success(bungDiaryService.findRegistrationData(detailId, user.getId())));
    }
}
