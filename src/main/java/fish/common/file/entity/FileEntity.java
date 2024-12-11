package fish.common.file.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name="FISH_BUN_FILE")
@Entity
@NoArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                   //파일넘버
    private String originFileName;     //사용자가 등록한
    private String systemFileName;     //시스템 상 파일 이름
    private String filePath;           //파일 경로
    private Long fileSize;             //파일 사이즈
    private String fileExtension;      //파일 확장자

    public FileEntity(String originFileName, String systemFileName, String filePath, Long fileSize, String fileExtension) {
        this.originFileName = originFileName;
        this.systemFileName = systemFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileExtension = fileExtension;
    }
}
