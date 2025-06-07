package fish.member.book.index.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDateDetail {
    private Long id;
    private String date;
    private Integer count;
}