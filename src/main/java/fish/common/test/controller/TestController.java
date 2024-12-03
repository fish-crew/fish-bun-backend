package fish.common.test.controller;

import fish.common.test.dto.TestRequest;
import fish.common.test.entity.TestEntity;
import fish.common.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    /**
     * localhost:8080/save를 호출 시 test 테이블에 데이터 적재
     *
     */

    @GetMapping("/save")
    public ResponseEntity<Object> save(TestRequest request) {
        // id의 값이 PK이기 때문에 id의 값을 변경하여 테스트
        request.toRequest("test", "test");
        TestEntity entity = request.toEntity(request);
        testService.save(entity);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * localhost:8080/find-all
     *
     * @return data
     */

    @GetMapping("/find-all")
    public Object findAll() {
        Map<String, Object> data = new HashMap<>();
        data.put("data", testService.findAll());
        return data;
    }

    /**
     * localhost:8080/find-one?id=test
     * 요렇게 테스트
     * @param id
     * @return
     */
    @GetMapping("/find-one")
    public Object findOne(String id) {
        Map<String, Object> data = new HashMap<>();
        data.put("data", testService.findById(id));
        return data;
    }

    /**
     * localhost:8080/delete?id=test
     * 요렇게 테스트
     * @param id
     * @return
     */

    @GetMapping("/delete")
    public Object delete(String id) {
        testService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
