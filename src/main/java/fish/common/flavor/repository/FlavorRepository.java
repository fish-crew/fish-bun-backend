package fish.common.flavor.repository;

import fish.common.flavor.entity.FishBunFlavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorRepository extends JpaRepository<FishBunFlavorEntity, Long> {
    List<FishBunFlavorEntity> findAllByOrderBySeqAsc();

    FishBunFlavorEntity findIconCodeById(Long id);
}
