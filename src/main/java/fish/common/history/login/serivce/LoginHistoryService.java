package fish.common.history.login.serivce;

import fish.common.history.login.entity.LoginHistory;
import fish.common.history.login.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginHistoryService {
    private final LoginHistoryRepository loginHistoryRepository;

    public void saveHistory(LoginHistory loginHistory) {
        loginHistoryRepository.save(loginHistory);
    }
}
