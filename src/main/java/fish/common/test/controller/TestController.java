package fish.common.test.controller;

import fish.common.test.dto.TestRequest;
import fish.common.test.entity.TestEntity;
import fish.common.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  localhost:8080/save를 호출 시 test 테이블에 데이터 적재
* */

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/save")
    public ResponseEntity<Object> save(TestRequest request) {
        // id의 값이 PK이기 때문에 id의 값을 변경하여 테스트
        request.toRequest("test", "test");
        TestEntity entity = request.toEntity(request);
        testService.save(entity);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
