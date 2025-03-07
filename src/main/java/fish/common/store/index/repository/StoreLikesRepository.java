package fish.common.store.index.repository;

import fish.common.store.index.entity.StoreLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreLikesRepository extends JpaRepository<StoreLikesEntity, Long> {
    Optional<StoreLikesEntity> findByStoreIdAndUserId(Long storeId, Long userId);
}