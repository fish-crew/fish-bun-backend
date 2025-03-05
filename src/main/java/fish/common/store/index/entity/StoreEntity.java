package fish.common.store.index.entity;

import fish.common.store.index.dto.request.StoreRegRequest;
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
    private LocalDateTime modDate;

    public static StoreEntity toEntity(StoreRegRequest request, Long userId, Long storeId) {
        return StoreEntity.builder()
                .id(storeId)
                .userId(userId)
                .address(request.getAddress())
                .name(request.getName())
                .detail(request.getDetail())
                .lat(request.getLat())
                .lng(request.getLng())
                .build()
                ;
    }

    public void modifyDetail(StoreEntity entity) {
        this.address = entity.getAddress();
        this.name = entity.getName();
        this.detail = entity.getDetail();
        this.lat = entity.getLat();
        this.lng = entity.getLng();
        this.modDate = LocalDateTime.now();
    }

}
