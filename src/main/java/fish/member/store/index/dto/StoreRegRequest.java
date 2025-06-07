package fish.member.store.index.dto;

import lombok.Getter;

@Getter
public class StoreRegRequest {
    private String address;
    private String name;
    private String detail;
    private Double lat;
    private Double lng;
}
