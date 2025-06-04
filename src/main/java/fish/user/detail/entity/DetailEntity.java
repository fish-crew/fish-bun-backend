package fish.user.detail.entity;

import fish.user.detail.converter.DetailFlavorConverter;
import fish.user.detail.dto.DetailFlavor;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FISH_BUN_DETAIL")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long storeId;
    @Convert(converter = DetailFlavorConverter.class)
    private List<DetailFlavor> flavors;
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