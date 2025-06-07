package fish.domain.diary.index;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BungDiaryRepository extends JpaRepository<BungDiary, Long> {
    Optional<BungDiary> findByIdAndUserId(Long detailId, Long userId);

    @Query(value = """
            SELECT 
                FBD.id, FBD.fileId, FBD.date, FBD.flavors, FBD.contents
                , U.nickname
            FROM BUNG_DIARY FBD
            LEFT JOIN USER U ON FBD.userId = U.id 
            WHERE FBD.storeId = :storeId
            """, nativeQuery = true)
    List<Map<String, Object>> findByStoreId(Long storeId);
}