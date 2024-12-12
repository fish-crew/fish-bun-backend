package fish.common.detail.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    private String flavors;
    @CreationTimestamp
    private LocalDateTime date;
    private Long fileId;
    public void addFileId(Long fileId) {
        this.fileId = fileId;
    }
}