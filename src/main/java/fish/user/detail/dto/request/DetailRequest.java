package fish.user.detail.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.user.detail.dto.DetailFlavor;
import fish.user.detail.entity.DetailEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Data
@RequiredArgsConstructor
public class DetailRequest {
    private String flavors;
    private MultipartFile picture;
    private String date;
    private String contents;
    private Long storeId;


    public DetailEntity toEntity(DetailRequest request, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DetailFlavor> detailFlavors = Arrays.asList(objectMapper.readValue(request.flavors, DetailFlavor[].class));
        return DetailEntity.builder()
                .flavors(detailFlavors)
                .userId(userId)
                .date(date)
                .contents(contents)
                .storeId(storeId)
                .build();
    }
}