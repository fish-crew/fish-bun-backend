package fish.domain.community.vote;

import fish.member.community.vote.dto.VoteRequest;
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
public class Vote {
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

    public static Vote toEntity(Long postId, Long userId, VoteRequest request) {
        return Vote.builder()
                .postId(postId)
                .userId(userId)
                .voteOption(request.getVoteOption())
                .build();
    }
}
