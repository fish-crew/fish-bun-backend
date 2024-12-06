package fish.common.user.repository;

import fish.common.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByProviderId(Long providerId);
    Optional<User> findByUuid(String uuid);
}