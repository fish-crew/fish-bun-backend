package fish.common.test.service;

import fish.common.test.entity.TestEntity;
import fish.common.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void save(TestEntity entity) {
        testRepository.save(entity);
    }
}
