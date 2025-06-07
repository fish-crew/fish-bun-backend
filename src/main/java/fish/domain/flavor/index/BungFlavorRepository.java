package fish.domain.flavor.index;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BungFlavorRepository extends JpaRepository<BungFlavorEntity, Long> {
    List<BungFlavorEntity> findAllByOrderBySeqAsc();

    BungFlavorEntity findIconCodeById(Long id);
}
