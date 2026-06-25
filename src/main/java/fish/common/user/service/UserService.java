package fish.common.user.service;

import fish.common.user.entity.User;
import fish.common.user.repository.UserRepository;
import fish.global.dto.BasePageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

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

    public User getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId)
                .orElseGet(() ->
                        null
                );
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void updateLastDate(User user) {
        user.updateLastDate();
        userRepository.save(user);
    }

    public void updateFirstLogin(User user) {
        user.updateIsFirstLogin();
        userRepository.save(user);
    }

    /**
     * Admin페이지의 유저 데이터를 가져오는 메소드
     * */
    public Map<String, Object> getList(BasePageDto dto) {
        Map<String, Object> result = new HashMap<>();

        int start = dto.getStart();
        int length = dto.getLength();
        if(start > 0) start = start/length;
        String order = dto.getOrder() == null ? "id" : dto.getOrder();

        Pageable pageable = PageRequest.of(start, length, Sort.by(Sort.Direction.ASC, order));
        Page<User> page = userRepository.findAll(pageable);

        long total = page.getTotalElements();
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", page.stream().toList());

        return result;
    }
  
}
