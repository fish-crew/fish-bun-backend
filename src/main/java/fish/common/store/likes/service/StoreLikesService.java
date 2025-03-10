package fish.common.store.likes.service;

import fish.common.store.likes.entity.StoreLikesEntity;
import fish.common.store.likes.repository.StoreLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreLikesService {
    private final StoreLikesRepository repository;

    @Transactional
    public void save(StoreLikesEntity entity) {
        Optional<StoreLikesEntity> optional = repository.findByStoreIdAndUserId(entity.getStoreId(), entity.getUserId());
        //유저당 가게 찜하기가 되어있을 시 찜 삭제
        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            repository.save(entity);
        }
    }
}