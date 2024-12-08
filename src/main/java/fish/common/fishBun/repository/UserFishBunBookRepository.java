package fish.common.fishBun.repository;

import fish.common.fishBun.entity.UserFishBunBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFishBunBookRepository extends JpaRepository<UserFishBunBook, Long> {
    List<UserFishBunBook> findAllByUserId(Long id);
}
