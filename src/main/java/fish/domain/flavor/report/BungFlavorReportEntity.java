package fish.domain.flavor.report;

import fish.member.flavor.dto.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "BUNG_FLAVOR_REPORT")
@Entity
@Getter
@NoArgsConstructor
public class BungFlavorReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flavor;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime regDate;

    @Builder
    public BungFlavorReportEntity(String flavor, Long userId, Status status) {
        this.flavor = flavor;
        this.userId = userId;
        this.status = status;
    }

    public static BungFlavorReportEntity toEntity(String flavor, Long userId) {
        return BungFlavorReportEntity.builder()
                .flavor(flavor)
                .userId(userId)
                .status(Status.PENDING)
                .build();
    }
}
