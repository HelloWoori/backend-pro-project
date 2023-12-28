package hellowoori.backendproproject.domain.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoveRepository extends JpaRepository<Love, Long> {

    Long countByArticleId(Long articleId);

    Love findByArticleIdAndUserId(Long articleId, UUID userId);
}
