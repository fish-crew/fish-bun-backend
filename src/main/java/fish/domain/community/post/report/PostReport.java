package fish.domain.community.post.report;

import fish.member.community.post.report.dto.PostReportRequest;
import fish.member.flavor.dto.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_REPORT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReport {
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

    public static PostReport toEntity(PostReportRequest request, Long userId) {
        return PostReport.builder()
                .contents(request.getContents())
                .userId(userId)
                .status(Status.PENDING)
                .build();
    }
}
