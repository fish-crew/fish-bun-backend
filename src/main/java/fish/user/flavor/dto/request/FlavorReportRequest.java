package fish.user.flavor.dto.request;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class FlavorReportRequest {
    @NotNull
    private String flavors;
}
