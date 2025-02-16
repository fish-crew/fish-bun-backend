package fish.common.book.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "USER_FISH_BUN_BOOK")
@Getter
@Entity
@NoArgsConstructor
public class UserBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long completedFlavorId;
    @CreationTimestamp
    private LocalDateTime regDate;

    @Builder
    public UserBookEntity(Long userId, Long completedFlavorId) {
        this.userId = userId;
        this.completedFlavorId = completedFlavorId;
    }

    public static UserBookEntity toEntity(Long userId, Long completedFlavorId) {
        return UserBookEntity.builder()
                .userId(userId)
                .completedFlavorId(completedFlavorId)
                .build();
    }
}
