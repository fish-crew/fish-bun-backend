package fish.common.fishBun.controller;

import fish.common.fishBun.service.FishBunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FishBunController {
    private final FishBunService fishBunService;
}
