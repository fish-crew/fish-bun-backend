package fish.common.detail.repository;

import fish.common.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long> {
    Optional<DetailEntity> findByIdAndUserId(Long detailId, Long userId);
}
