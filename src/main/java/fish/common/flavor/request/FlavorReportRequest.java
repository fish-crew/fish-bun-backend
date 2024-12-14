package fish.common.flavor.request;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class FlavorReportRequest {
    @NotNull
    private String flavors;
}
