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


    public Long getUserId(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found with UUID: " + uuid));
        return user.getProviderId();
    }


    public Boolean isExistedId(Long id) {
        return userRepository.existsByProviderId(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
