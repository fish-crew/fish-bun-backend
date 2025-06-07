package fish.domain.book;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "BUNG_DIARY_BOOK")
@Getter
@Entity
@NoArgsConstructor
public class BungDiaryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long completedFlavorId;
    @CreationTimestamp
    private LocalDateTime regDate;
    @Setter
    private Double rating;

    @Builder
    public BungDiaryBook(Long userId, Long completedFlavorId) {
        this.userId = userId;
        this.completedFlavorId = completedFlavorId;
    }

    public static BungDiaryBook toEntity(Long userId, Long completedFlavorId) {
        return BungDiaryBook.builder()
                .userId(userId)
                .completedFlavorId(completedFlavorId)
                .build();
    }
}