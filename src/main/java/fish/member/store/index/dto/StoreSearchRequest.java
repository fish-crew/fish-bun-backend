package fish.member.store.index.dto;

import lombok.Data;

@Data
public class StoreSearchRequest {
    private Double minLat;
    private Double minLng;
    private Double maxLat;
    private Double maxLng;
}