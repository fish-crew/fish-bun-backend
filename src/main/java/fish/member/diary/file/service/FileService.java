package fish.member.diary.file.service;

import fish.domain.diary.file.BungDiaryFile;
import fish.domain.diary.file.BungDIaryFileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file-uri}")
    private String fileUri;

    private final BungDIaryFileRepository bungDIaryFileRepository;

    public void save(BungDiaryFile file) {
        bungDIaryFileRepository.save(file);
    }

    public BungDiaryFile findFileById(Long id) {
        return bungDIaryFileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("File Entity not founded with Id: " + id));
    }

    public String getFileUrl(Long fileId) {
        BungDiaryFile bungDiaryFile = bungDIaryFileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File data not found with id: " + fileId));
        return fileUri + bungDiaryFile.getFilePath() + bungDiaryFile.getSystemFileName();
    }
}
