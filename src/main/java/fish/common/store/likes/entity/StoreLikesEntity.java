package fish.common.store.likes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "STORE_LIKES")
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreLikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long storeId;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static StoreLikesEntity toEntity(Long userId, Long storeId) {
        return StoreLikesEntity.builder()
                .storeId(storeId)
                .userId(userId)
                .build()
                ;
    }
}