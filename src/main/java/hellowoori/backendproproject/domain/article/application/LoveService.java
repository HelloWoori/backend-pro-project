package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.article.domain.Love;
import hellowoori.backendproproject.domain.article.domain.LoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LoveService {

    private final LoveRepository loveRepository;

    public Long countLove(Long articleId) {
        return loveRepository.countByArticleId(articleId);
    }

    public boolean switchLoveStatus(Long articleId, UUID userId) {
        Love existingLove = loveRepository.findByArticleIdAndUserId(articleId, userId);

        if (existingLove != null) {
            //이미 행이 있는 경우, 좋아요 취소
            loveRepository.deleteByUserIdAndArticleId(articleId, userId);
            return false;
        } else {
            //행이 없는 경우, 좋아요 추가
            Love newLove = new Love(articleId, userId);
            loveRepository.save(newLove);
            return true;
        }
    }
}
