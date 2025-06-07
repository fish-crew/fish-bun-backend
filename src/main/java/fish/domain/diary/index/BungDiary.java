package fish.domain.diary.index;

import fish.member.diary.index.converter.BungDiaryFlavorConverter;
import fish.member.diary.index.dto.BungDiaryFlavor;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BUNG_DIARY")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BungDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long storeId;
    @Convert(converter = BungDiaryFlavorConverter.class)
    private List<BungDiaryFlavor> flavors;
    @CreationTimestamp
    private LocalDateTime regDate;
    private String date;
    private Long fileId;
    private String contents;
    public void addFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}