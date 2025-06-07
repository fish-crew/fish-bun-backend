package fish.global.dto;

import lombok.Data;

@Data
public class BasePageDto {
    private int start;
    private int length;
    private Long rowNum;
    private String order; // 정렬 컬럼
    private String orderDir; // 정렬 기준
}
