package fish.domain.community.post.index;

import fish.member.community.post.index.dto.PostRequest;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    private String contents;
    private String firstOption;
    private String secondOption;
    private String fileIdList;
    @CreationTimestamp
    private LocalDateTime regDate;

    public void setFileIdList(String fileIdList) {
        this.fileIdList = fileIdList;
    }

    public void updateFileIdList(String JsonFileIdList) {
        this.fileIdList = "[" + this.fileIdList.substring(1, this.fileIdList.length() - 1) + ","
                + JsonFileIdList.substring(1, JsonFileIdList.length() - 1) + "]";
    }

    public void modify(PostRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();
        this.firstOption = request.getFirstOption();
        this.secondOption = request.getSecondOption();
        this.regDate = LocalDateTime.now();
    }

    public static Post toEntity(PostRequest request) {
        return Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .firstOption(request.getFirstOption())
                .secondOption(request.getSecondOption())
                .build();
    }
}
