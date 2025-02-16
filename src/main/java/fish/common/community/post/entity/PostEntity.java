package fish.common.community.post.entity;

import fish.common.community.post.dto.request.PostRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "POST")
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
    private Long firstOptionCount;
    private Long secondOptionCount;
    private String fileIdList;
    @CreationTimestamp
    private LocalDateTime regDate;

    public void setFileIdList(String fileIdList) {
        this.fileIdList = fileIdList;
    }

    public static PostEntity toEntity(PostRequest request) {
        return PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .firstOption(request.getFirstOption())
                .secondOption(request.getSecondOption())
                .firstOptionCount(0L)
                .secondOptionCount(0L)
                .build();
    }
}
