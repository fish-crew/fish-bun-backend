package fish.common.detail.service;

import fish.common.file.entity.FileEntity;
import fish.common.file.service.FileService;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.repository.DetailRepository;
import fish.core.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;
    private final FileService fileService;

    @Transactional
    public void save(DetailEntity entity, MultipartFile picture) throws IOException {
        FileEntity fileEntity = FileUtils.fileUpload(picture, "fishbun");
        fileService.save(fileEntity);
        entity.addFileId(fileEntity.getId());
        detailRepository.save(entity);
    }
}
