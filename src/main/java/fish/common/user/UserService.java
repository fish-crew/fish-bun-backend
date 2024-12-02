package fish.common.user;

import fish.common.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Long getUserId(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found with UUID: " + uuid));

        return user.getId();
    }

    public List<User> saveAllTestUser(List<User> testUserList) {
        return userRepository.saveAll(testUserList);
    }
}
