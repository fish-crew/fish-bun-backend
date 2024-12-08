package fish.common.flavor.repository;

import fish.common.flavor.entity.FishBunFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlavorRepository extends JpaRepository<FishBunFlavor, Long> {
}
