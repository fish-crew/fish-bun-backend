package fish.common.fishBun.repository;

import fish.common.fishBun.entity.FishBun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishBunBookRepository extends JpaRepository<FishBun, String> {
}
