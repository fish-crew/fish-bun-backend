package fish.common.test.service;

import fish.common.test.entity.TestEntity;
import fish.common.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void save(TestEntity entity) {
        testRepository.save(entity);
    }
    public List<TestEntity> findAll() {
        return testRepository.findAll();
    }
    public TestEntity findById(String id) {
        return testRepository.findById(id).orElse(null);
    }
    public void deleteById(String id) {
        testRepository.deleteById(id);
    }
}
