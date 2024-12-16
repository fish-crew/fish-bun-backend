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
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long completedFlavorId;
    @CreationTimestamp
    private LocalDateTime date;

    @Builder
    public UserBook(Long userId, Long completedFlavorId) {
        this.userId = userId;
        this.completedFlavorId = completedFlavorId;
    }

    public static UserBook toEntity(Long userId, Long completedFlavorId) {
        return UserBook.builder()
                .userId(userId)
                .completedFlavorId(completedFlavorId)
                .build();
    }
}
