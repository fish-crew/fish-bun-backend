package fish.user.community.post.entity;

import fish.user.community.post.dto.request.PostReportRequest;
import fish.user.flavor.dto.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_REPORT")
@Getter
@Builder
public class PostReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String contents;
    @NotNull
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static PostReportEntity toEntity(PostReportRequest request, Long userId) {
        return PostReportEntity.builder()
                .contents(request.getContents())
                .userId(userId)
                .status(Status.PENDING)
                .build();
    }
}
