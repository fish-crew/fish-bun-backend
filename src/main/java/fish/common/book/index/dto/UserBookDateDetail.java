package fish.common.book.index.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserBookDateDetail {
    private Long id;
    private String date;
    private Integer count;
}