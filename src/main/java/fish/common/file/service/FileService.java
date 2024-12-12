package fish.common.file.service;

import fish.common.file.entity.FileEntity;
import fish.common.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public void save(FileEntity file) {
        fileRepository.save(file);
    }
}
