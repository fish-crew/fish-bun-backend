package fish.common.fishBun.service;

import fish.common.fishBun.repository.FishBunBookRepository;
import fish.common.fishBun.repository.FishBunCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FishBunService {
    private final FishBunCalendarRepository fishBunCalendarRepository;
    private final FishBunBookRepository fishBunBookRepository;
}
