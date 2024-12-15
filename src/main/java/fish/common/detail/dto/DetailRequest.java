package fish.common.detail.dto;

import fish.common.detail.entity.DetailEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
@RequiredArgsConstructor
public class DetailRequest {
    private List<DetailFlavor> flavors;
    private MultipartFile picture;

    public DetailEntity toEntity(DetailRequest request, Long userId) {
        return DetailEntity.builder()
                .flavors(request.flavors.toString())
                .userId(userId)
                .build();
    }
}