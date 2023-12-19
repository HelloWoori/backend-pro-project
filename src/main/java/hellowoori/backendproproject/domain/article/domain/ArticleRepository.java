package hellowoori.backendproproject.domain.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByGatheringId(Long gatheringId);
    List<Article> findByGatheringIdAndIsBlockedIsFalse(Long gatheringId);

    Optional<Article> findByArticleIdAndIsBlockedIsFalse(Long articleId);
}
