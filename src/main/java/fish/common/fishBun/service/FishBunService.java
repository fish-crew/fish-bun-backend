package fish.common.fishBun.service;

import fish.common.fishBun.dto.response.CalendarResDTO;
import fish.common.fishBun.repository.FishBunBookRepository;
import fish.common.fishBun.repository.FishBunCalendarRepository;

import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FishBunService {
    private final FishBunCalendarRepository fishBunCalendarRepository;
    private final FishBunBookRepository fishBunBookRepository;
    private final UserService userService;

    public List<CalendarResDTO> findAllCalendarDate(String userUUID) {
        Long userId = userService.getUserId(userUUID);
        return fishBunCalendarRepository.findAllByUserId(userId).stream()
                .map(CalendarResDTO::toResponseDTO)
                .collect(Collectors.toList());
    }
}
