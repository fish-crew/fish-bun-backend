package fish.common.store.index.dto.request;

import lombok.Getter;

@Getter
public class StoreSearchRequest {
    private Double minLat;
    private Double minLng;
    private Double maxLat;
    private Double maxLng;
}