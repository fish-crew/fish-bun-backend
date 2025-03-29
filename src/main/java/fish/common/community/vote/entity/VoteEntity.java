package fish.common.community.vote.entity;

import fish.common.community.vote.dto.request.VoteRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VOTE")
@Builder
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long postId;
    @NotNull
    private Long userId;
    private String voteOption;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static VoteEntity toEntity(Long postId, Long userId, VoteRequest request) {
        return VoteEntity.builder()
                .postId(postId)
                .userId(userId)
                .voteOption(request.getVoteOption())
                .build();
    }
}
