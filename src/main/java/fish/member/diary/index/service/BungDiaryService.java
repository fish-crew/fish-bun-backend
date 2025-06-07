package fish.member.diary.index.service;

import fish.member.diary.index.dto.BungDiaryResponse;
import fish.domain.diary.file.BungDiaryFile;
import fish.member.diary.file.service.FileService;
import fish.domain.diary.index.BungDiary;
import fish.domain.diary.index.BungDiaryRepository;
import fish.domain.flavor.index.BungFlavorRepository;
import fish.common.util.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BungDiaryService {

    private final BungDiaryRepository bungDiaryRepository;
    private final BungFlavorRepository bungFlavorRepository;
    private final FileService fileService;

    @Transactional
    public Long save(BungDiary entity, MultipartFile picture) throws IOException {
        BungDiaryFile bungDiaryFile = FileUtils.fileUpload(picture, "fishbun");
        fileService.save(bungDiaryFile);
        entity.addFileId(bungDiaryFile.getId());
        return bungDiaryRepository.save(entity).getId();
    }

    public List<BungDiaryResponse> findRegistrationData(Long detailId, Long userId) {
        BungDiary bungDiary = bungDiaryRepository.findByIdAndUserId(detailId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Detail Entity not found with ID: " + detailId));

        return bungDiary.getFlavors().stream()
                .map(detailFlavor -> {
                    Long flavorId = detailFlavor.getFlavorId();
                    String iconCode = bungFlavorRepository.findIconCodeById(flavorId).getIconCode();
                    return BungDiaryResponse.toResponse(flavorId, iconCode);
                })
                .collect(Collectors.toList())
                ;
    }

    public List<Map<String, Object>> findByStoreId(Long storeId) {
        return bungDiaryRepository.findByStoreId(storeId);
    }
}
