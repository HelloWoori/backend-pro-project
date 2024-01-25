package hellowoori.backendproproject.domain.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByIdAndUserId(Long articleId, UUID userId);

    List<Article> findAllByCommunityId(Long communityId);
}
