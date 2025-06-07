package fish.domain.bungbal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BungbalRepository extends JpaRepository<Bungbal, Long> {
    Optional<Bungbal> findByMbti(String mbti);

    @Query(value= "SELECT SUM(count) FROM BUNGBAL", nativeQuery = true)
    Long getTotalCount();
}



