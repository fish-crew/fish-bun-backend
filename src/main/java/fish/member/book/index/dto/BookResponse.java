package fish.member.book.index.dto;

import fish.domain.book.BungDiaryBook;
import fish.domain.flavor.index.BungFlavorEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponse {
    private Long id;
    private Long completedFlavorId;
    private LocalDateTime regDate;
    private String iconCode;
    private int seq;


    @Builder
    public BookResponse(Long id, Long completedFlavorId, LocalDateTime date, String iconCode, int seq) {
        this.id = id;
        this.completedFlavorId = completedFlavorId;
        this.regDate = date;
        this.iconCode = iconCode;
        this.seq = seq;
    }

    public static BookResponse toResponseDTO(BungDiaryBook userFishBunBook, BungFlavorEntity flavor) {
        return BookResponse.builder()
                .id(userFishBunBook.getId())
                .completedFlavorId(userFishBunBook.getCompletedFlavorId())
                .date(userFishBunBook.getRegDate())
                .iconCode(flavor.getIconCode())
                .seq(flavor.getSeq())
                .build();
    }
}
