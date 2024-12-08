package fish.common.book.controller;

import fish.common.book.response.UserBookResponse;
import fish.common.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
public class BookController {
    private final BookService bookService;


    @GetMapping(value = "/book/user/{userUUID}")
    public ResponseEntity<List<UserBookResponse>> getUserFishBunBookList(@PathVariable("userUUID") String userUUID) {
        return ResponseEntity.ok(bookService.findAllUserFishBunBook(userUUID));
    }
}
