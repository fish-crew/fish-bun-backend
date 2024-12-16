package fish.common.detail.dto;

import fish.common.detail.entity.DetailEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class DetailRequest {
    private String flavors;
    private MultipartFile picture;

    public DetailEntity toEntity(DetailRequest request, Long userId) {
        return DetailEntity.builder()
                .flavors(request.flavors)
                .userId(userId)
                .build();
    }
}