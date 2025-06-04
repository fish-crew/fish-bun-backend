package fish.user.book.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserBookRatingDetail {
    private Long id;
    private String flavor;
    private String iconCode;
    private Integer seq;
    private String description;
    private String highlight;
    private LocalDateTime regDate;
    private Double myRating;
    private Double avgRating;
    private Integer ratingCount;
}