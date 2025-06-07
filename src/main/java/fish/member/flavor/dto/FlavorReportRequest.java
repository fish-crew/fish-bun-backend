package fish.member.flavor.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class FlavorReportRequest {
    @NotNull
    private String flavors;
}
