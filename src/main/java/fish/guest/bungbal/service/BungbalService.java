package fish.guest.bungbal.service;

import fish.domain.bungbal.Bungbal;
import fish.domain.bungbal.BungbalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BungbalService {
    private final BungbalRepository bungbalRepository;

    public void count(String mbti) {
        Bungbal bungbal = bungbalRepository.findByMbti(mbti)
                .orElseThrow(() -> new IllegalArgumentException("MBTI에 해당하는 붕어빵이 없습니다."));
        bungbal.incrementCount(); // count 증가
        bungbalRepository.save(bungbal); // 변경 사항 저장
    }

    public Long getTotalCount() {
        return bungbalRepository.getTotalCount();
    }

    public List<Bungbal> getStats() {
        return bungbalRepository.findAll();
    }

}
