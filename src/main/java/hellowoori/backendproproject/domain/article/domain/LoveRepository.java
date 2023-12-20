package hellowoori.backendproproject.domain.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.UUID;

public interface LoveRepository extends JpaRepository<Love, Long> {

    Long countByArticleId(Long articleId);

    Love findByArticleIdAndUserId(Long articleId, UUID userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Love l WHERE l.articleId = :articleId AND l.userId = :userId")
    void deleteByUserIdAndArticleId(@Param("articleId") Long articleId, @Param("userId") UUID userId);
}
