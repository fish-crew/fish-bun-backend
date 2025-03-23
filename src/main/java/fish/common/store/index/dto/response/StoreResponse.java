package fish.common.store.index.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class StoreResponse {
    private Long id;
    private String name;
    private String detail;
    private String nickname;
    private String address;
    private Double lat;
    private Double lng;
    private String likeYn;
    private int likes;
    private int diaryCount;
    private String regDate;
    private List<StoreDetailResponse> details;

    @Builder
    public StoreResponse(Long id, String name, String detail, String nickname, String address,
                         Double lat, Double lng, String likeYn, int likes, int diaryCount,String regDate,
                         List<StoreDetailResponse> details) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.nickname = nickname;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.likeYn = likeYn;
        this.likes = likes;
        this.diaryCount = diaryCount;
        this.regDate = regDate;
        this.details = details;
    }

    public static List<StoreResponse> toResponseList(List<Map<String, Object>> dataList) {
        return dataList.stream()
                .map(data -> StoreResponse.builder()
                        .id(Long.parseLong(data.get("id").toString()))
                        .name(data.get("name").toString())
                        .detail(data.get("detail").toString())
                        .nickname(data.get("nickname").toString())
                        .address(data.get("address").toString())
                        .lat(Double.parseDouble(data.get("lat").toString()))
                        .lng(Double.parseDouble(data.get("lng").toString()))
                        .likeYn(data.get("likeYn").toString())
                        .likes(Integer.parseInt(data.get("likes").toString()))
                        .diaryCount(Integer.parseInt(data.get("diaryCount").toString()))
                        .regDate(data.get("regDate").toString())
                        .build())
                .collect(Collectors.toList())
                ;
    }

    public static StoreResponse toResponse(Map<String, Object> data, List<StoreDetailResponse> details) {
        return StoreResponse.builder()
                .id(Long.parseLong(data.get("id").toString()))
                .name(data.get("name").toString())
                .detail(data.get("detail").toString())
                .nickname(data.get("nickname").toString())
                .address(data.get("address").toString())
                .lat(Double.parseDouble(data.get("lat").toString()))
                .lng(Double.parseDouble(data.get("lng").toString()))
                .likeYn(data.get("likeYn").toString())
                .likes(Integer.parseInt(data.get("likes").toString()))
                .regDate(data.get("regDate").toString())
                .details(details)
                .build()
                ;
    }
}
