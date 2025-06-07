package fish.member.diary.index.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.domain.diary.index.BungDiary;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BungDiaryRequest {
    private String flavors;
    private MultipartFile picture;
    private String date;
    private String contents;
    private Long storeId;


    public BungDiary toEntity(BungDiaryRequest request, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BungDiaryFlavor> bungDiaryFlavors = Arrays.asList(objectMapper.readValue(request.flavors, BungDiaryFlavor[].class));
        return BungDiary.builder()
                .flavors(bungDiaryFlavors)
                .userId(userId)
                .date(date)
                .contents(contents)
                .storeId(storeId)
                .build();
    }
}