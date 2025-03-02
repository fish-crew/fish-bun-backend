package fish.common.store.index.entity;

import fish.common.store.index.dto.request.StoreRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "STORE")
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String address;
    private String name;
    private String detail;
    private Double lat;
    private Double lng;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static StoreEntity toEntity(StoreRequest request, Long userId) {
        return StoreEntity.builder()
                .userId(userId)
                .address(request.getAddress())
                .name(request.getName())
                .detail(request.getDetail())
                .lat(request.getLat())
                .lng(request.getLng())
                .build()
                ;
    }

}
