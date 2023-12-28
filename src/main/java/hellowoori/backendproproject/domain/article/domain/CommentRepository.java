package hellowoori.backendproproject.domain.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(Long articleId);

    @Query("SELECT c FROM Comment c WHERE c.id = :id AND c.userId = :userId")
    Optional<Comment> findByIdAndUserId(@Param("id") Long id, @Param("userId") UUID userId);
}
