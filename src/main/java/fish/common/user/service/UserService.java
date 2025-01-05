package fish.common.user.service;

import fish.common.user.entity.User;
import fish.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    // 서드파티 에서 제공한 providerId가 존재 시에 User 리턴, 존재하지 않으면 User 등록
    public User saveUser(User user) {
        return userRepository.findByProviderId(user.getProviderId())
                .orElseGet(() ->
                        userRepository.save(user)
                );
    }

    public User getUserById(long providerId) {
        return userRepository.findByProviderId(providerId)
                .orElseGet(() ->
                        null
                );
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
