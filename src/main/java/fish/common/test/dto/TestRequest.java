package fish.common.test.dto;

import fish.common.test.entity.TestEntity;
import lombok.Getter;

@Getter
public class TestRequest {
    private String id;
    private String name;

    public TestEntity toEntity(TestRequest request) {
        return TestEntity.builder()
                .id(request.id)
                .name(request.name)
                .build();
    }

    public TestRequest toRequest(String id, String name) {
        TestRequest request = new TestRequest();
        this.id = id;
        this.name = name;
        return request;
    }
}
