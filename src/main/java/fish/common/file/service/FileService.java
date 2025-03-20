package fish.common.file.service;

import fish.common.file.entity.FileEntity;
import fish.common.file.repository.FileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file-uri}")
    private String fileUri;

    private final FileRepository fileRepository;

    public void save(FileEntity file) {
        fileRepository.save(file);
    }

    public FileEntity findFileById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("File Entity not founded with Id: " + id));
    }

    public String getFileUrl(Long fileId) {
        FileEntity fileEntity = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File data not found with id: " + fileId));
        return fileUri + fileEntity.getFilePath() + fileEntity.getSystemFileName();
    }
}
