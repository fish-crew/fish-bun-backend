package fish.common.detail.controller;

import fish.common.book.index.service.BookService;
import fish.common.detail.dto.DetailFlavor;
import fish.common.detail.dto.request.DetailRequest;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.dto.response.DetailResponse;
import fish.common.detail.service.DetailService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
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
@RequestMapping(value = "/fish-bun/detail")
@Tag(name = "붕어빵 등록 API")
public class DetailController {
    private final DetailService detailService;
    private final BookService bookService;

    @Operation(summary = "붕어빵 일지 등록", description = "먹은 붕어빵 종류 및 개수와 사진을 등록하는 API")
    @PostMapping(value = "/save", consumes = {"multipart/form-data"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUtil<Long>> save(@ModelAttribute DetailRequest request,
                                                   @AuthenticationPrincipal User user) throws IOException {
        DetailEntity entity = request.toEntity(request, user.getId());
        Long id = detailService.save(entity, request.getPicture());
        // Update the user book for a new flavor
        List<Long> flavorIdList = entity.getFlavors().stream().map(DetailFlavor::getFlavorId).toList();
        bookService.saveUserCompletedFlavor(flavorIdList, user.getId());

        return ResponseEntity.ok(ResponseUtil.success(id));
    }

    @Operation(summary = "등록 완료 데이터 조회", description = "등록 완료 붕어빵 icon code 반환 API")
    @GetMapping(value = "/save-success/{detailId}")
    public ResponseEntity<ResponseUtil<List<DetailResponse>>> findRegistrationData(@PathVariable Long detailId,
                               @AuthenticationPrincipal User user) throws IOException {
        return ResponseEntity.ok(ResponseUtil.success(detailService.findRegistrationData(detailId, user.getId())));
    }
}
