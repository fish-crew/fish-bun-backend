package fish.common.bungbal.controller;

import fish.common.bungbal.dto.BungbalRequest;
import fish.common.bungbal.service.BungbalService;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bungbal/stats")
public class BungbalController {
    public final BungbalService bungbalService;

    @GetMapping()
    public ResponseUtil getStats() {
        Map<Object, Object> additionalData = new HashMap<>();
        additionalData.put("total", bungbalService.getTotalCount());
        return ResponseUtil.success(bungbalService.getStats(), additionalData);
    }

    @PostMapping("/count")
    public ResponseUtil count(@RequestBody BungbalRequest request) {
        bungbalService.count(request.getMbti());
        return ResponseUtil.success();
    }
}
