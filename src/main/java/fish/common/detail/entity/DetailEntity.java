package fish.common.detail.entity;

import fish.common.detail.converter.DetailFlavorConverter;
import fish.common.detail.dto.DetailFlavor;
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
    @Convert(converter = DetailFlavorConverter.class)
    private List<DetailFlavor> flavors;
    @CreationTimestamp
    private LocalDateTime regDate;
    private String date;
    private Long fileId;
    public void addFileId(Long fileId) {
        this.fileId = fileId;
    }
}