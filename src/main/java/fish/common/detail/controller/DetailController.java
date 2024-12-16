package fish.common.detail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.book.service.BookService;
import fish.common.detail.dto.DetailFlavor;
import fish.common.detail.dto.DetailRequest;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.service.DetailService;
import fish.common.user.entity.User;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/detail")
public class DetailController {
    private final DetailService detailService;
    private final BookService bookService;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public ResponseEntity save(@ModelAttribute DetailRequest request,
                               @AuthenticationPrincipal User user) throws IOException {
        DetailEntity entity = request.toEntity(request, user.getId());
        Long id = detailService.save(entity, request.getPicture());
        // Update the user book for a new flavor
        DetailFlavor[] detailFlavors = objectMapper.readValue(entity.getFlavors(), DetailFlavor[].class);
        List<Long> flavorIdList = Arrays.stream(detailFlavors).map(DetailFlavor::getFlavorId).toList();
        bookService.saveUserCompletedFlavor(flavorIdList, user.getId());

        return ResponseEntity.ok(ResponseUtil.success(id));
    }
}
