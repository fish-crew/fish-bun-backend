package fish.member.user.history.serivce;

import fish.domain.user.history.UserHistory;
import fish.domain.user.history.UserHistoryRepository;
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
