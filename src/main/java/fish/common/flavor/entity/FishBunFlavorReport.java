package fish.common.flavor.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "FISH_BUN_FLAVOR_REPORT")
@Entity
@Getter
@NoArgsConstructor
public class FishBunFlavorReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flavor;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public FishBunFlavorReport(String flavor, Long userId, Status status) {
        this.flavor = flavor;
        this.userId = userId;
        this.status = status;
    }

    public static FishBunFlavorReport toEntity(String flavor, Long userId) {
        return FishBunFlavorReport.builder()
                .flavor(flavor)
                .userId(userId)
                .status(Status.PENDING)
                .build();
    }
}
