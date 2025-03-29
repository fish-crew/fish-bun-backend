package fish.common.detail.service;

import fish.common.detail.dto.response.DetailResponse;
import fish.common.file.entity.FileEntity;
import fish.common.file.service.FileService;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.repository.DetailRepository;
import fish.common.flavor.repository.FlavorRepository;
import fish.global.util.FileUtils;
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
public class DetailService {

    private final DetailRepository detailRepository;
    private final FlavorRepository flavorRepository;
    private final FileService fileService;

    @Transactional
    public Long save(DetailEntity entity, MultipartFile picture) throws IOException {
        FileEntity fileEntity = FileUtils.fileUpload(picture, "fishbun");
        fileService.save(fileEntity);
        entity.addFileId(fileEntity.getId());
        return detailRepository.save(entity).getId();
    }

    public List<DetailResponse> findRegistrationData(Long detailId, Long userId) {
        DetailEntity detailEntity = detailRepository.findByIdAndUserId(detailId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Detail Entity not found with ID: " + detailId));

        return detailEntity.getFlavors().stream()
                .map(detailFlavor -> {
                    Long flavorId = detailFlavor.getFlavorId();
                    String iconCode = flavorRepository.findIconCodeById(flavorId).getIconCode();
                    return DetailResponse.toResponse(flavorId, iconCode);
                })
                .collect(Collectors.toList())
                ;
    }

    public List<Map<String, Object>> findByStoreId(Long storeId) {
        return detailRepository.findByStoreId(storeId);
    }
}
