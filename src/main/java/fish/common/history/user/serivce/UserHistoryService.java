package fish.common.history.user.serivce;

import fish.common.history.user.entity.UserHistory;
import fish.common.history.user.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserHistoryService {
    private final UserHistoryRepository userHistoryRepository;

    public void saveHistory(UserHistory userHistory) {
        userHistoryRepository.save(userHistory);
    }
}
