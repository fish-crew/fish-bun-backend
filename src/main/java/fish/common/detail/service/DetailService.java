package fish.common.detail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.detail.dto.DetailFlavor;
import fish.common.detail.response.DetailResponse;
import fish.common.file.entity.FileEntity;
import fish.common.file.service.FileService;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.repository.DetailRepository;
import fish.common.flavor.repository.FlavorRepository;
import fish.core.util.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;
    private final FlavorRepository flavorRepository;
    private final FileService fileService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public Long save(DetailEntity entity, MultipartFile picture) throws IOException {
        FileEntity fileEntity = FileUtils.fileUpload(picture, "fishbun");
        fileService.save(fileEntity);
        entity.addFileId(fileEntity.getId());
        return detailRepository.save(entity).getId();
    }

    public List<DetailResponse> findRegistrationData(Long detailId) throws JsonProcessingException {
        DetailEntity detailEntity = detailRepository.findById(detailId)
                .orElseThrow(() -> new EntityNotFoundException("Detail Entity not found with ID: " + detailId));
        DetailFlavor[] detailFlavors = objectMapper.readValue(detailEntity.getFlavors(), DetailFlavor[].class);

        return Arrays.stream(detailFlavors)
                .map(detailFlavor -> {
                    Long flavorId = detailFlavor.getFlavorId();
                    String iconCode = flavorRepository.findIconCodeById(flavorId).getIconCode();
                    return DetailResponse.toResponse(flavorId, iconCode);
                })
                .collect(Collectors.toList());
    }
}
