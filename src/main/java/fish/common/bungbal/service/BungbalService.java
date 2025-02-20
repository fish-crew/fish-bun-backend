package fish.common.bungbal.service;

import fish.common.bungbal.entity.BungbalEntity;
import fish.common.bungbal.repository.BungbalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BungbalService {
    private final BungbalRepository bungbalRepository;

    public void count(String mbti) {
        BungbalEntity bungbal = bungbalRepository.findByMbti(mbti)
                .orElseThrow(() -> new IllegalArgumentException("MBTI에 해당하는 붕어빵이 없습니다."));
        bungbal.incrementCount(); // count 증가
        bungbalRepository.save(bungbal); // 변경 사항 저장
    }

    public Long getTotalCount() {
        return bungbalRepository.getTotalCount();
    }

    public List<BungbalEntity> getStats() {
        return bungbalRepository.findAll();
    }

}
