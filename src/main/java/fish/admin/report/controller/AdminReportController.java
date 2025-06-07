package fish.admin.report.controller;

import fish.common.community.post.service.PostService;
import fish.common.flavor.service.FlavorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Tag(name = "[관리자] 기능 API")
public class AdminReportController {
    private final FlavorService flavorService;
    private final PostService postService;

    @GetMapping(value = "/list")
    public String viewReport() {
        return "main/report/list";
    }

    @Operation(summary = "[관리자] 제보된 붕어빵 조회")
    @GetMapping(value = "/list.json")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> listReport() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("data", flavorService.findAllReports());
        return result;
    }
}
