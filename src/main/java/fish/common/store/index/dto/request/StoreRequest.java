package fish.common.store.index.dto.request;

import lombok.Data;
import lombok.Getter;

@Getter
public class StoreRequest {
    private String address;
    private String name;
    private String detail;
    private Double lat;
    private Double lng;
}
