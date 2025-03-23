package fish.common.store.index.repository;

import fish.common.store.index.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query(value = """
            SELECT 
                S.id, S.address, S.name, S.detail, S.lat, S.lng, S.regDate, U.nickname, 
                COUNT(DISTINCT FBD.id) AS diaryCount,
                COUNT(DISTINCT SL.id) AS likes,
                MAX(CASE WHEN SL.userId = :userId THEN 'Y' ELSE 'N' END) AS likeYn
            FROM STORE S
            LEFT JOIN USER U ON S.userId = U.id
            LEFT JOIN STORE_LIKES SL ON S.id = SL.storeId
            LEFT JOIN FISH_BUN_DETAIL FBD ON S.id = FBD.storeId
            WHERE S.lat BETWEEN :minLat AND :maxLat
            AND S.lng BETWEEN :minLng AND :maxLng
            GROUP BY S.id, U.id
            """, nativeQuery = true)
    List<Map<String, Object>> findByLatLngBounds(@Param("minLat") Double minLat,
                                                           @Param("maxLat") Double maxLat,
                                                           @Param("minLng") Double minLng,
                                                           @Param("maxLng") Double maxLng,
                                                           @Param("userId") Long userId);

    @Query(value = """
            SELECT S.id, S.address, S.name, S.detail, S.lat, S.lng, S.regDate, U.nickname, COUNT(SL.storeId) AS likes,
            MAX(CASE WHEN SL.userId = :userId THEN 'Y' ELSE 'N' END) AS likeYn
            FROM STORE S
            LEFT JOIN USER U ON S.userId = U.id 
            LEFT JOIN STORE_LIKES SL ON S.id = SL.storeId 
            WHERE S.id = :storeId
            """, nativeQuery = true)
    Map<String, Object> findByStoreId(@Param("storeId") Long storeId, @Param("userId") Long userId);
}
