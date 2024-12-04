package fish.common.user;

import fish.common.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // data.sql 에서는 uuid 임의로 삽입하여 데이터 초기화
    // uuid 필드 자동 생성하여 영속화된 entity를 DB에 잘 저장하는지 확인하는 Test user 생성 api
    @GetMapping(value = "/test-user/save")
    public ResponseEntity<List<User>> saveTestUser() {
        List<User> users = List.of(
                new User("191a0466-53d9-4c6d-9d95-71588d62658f", "fjuwrmy.png", "팥죽이", 1L),
                new User("42a214bb-120e-4385-b2d9-0cfc6b2b8432", "duwsjqf.png", "Fania", 1L),
                new User("ecdcda08-fb80-46cd-98ca-8db271515bbf", "xrulamo.png", "Thomasina", 2L)
        );

        return ResponseEntity.ok(userService.saveAllTestUser(users));
    }
}
