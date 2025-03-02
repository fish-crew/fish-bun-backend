package fish.common.store.index.service;

import fish.common.store.index.entity.StoreEntity;
import fish.common.store.index.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public void save(StoreEntity entity) {
        storeRepository.save(entity);
    }

    public void findById(String id) {

    }
}
