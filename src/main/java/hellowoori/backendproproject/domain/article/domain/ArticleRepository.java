package hellowoori.backendproproject.domain.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a.id FROM Article a WHERE a.communityId = :communityId")
    List<Long> findIdsByCommunityId(@Param("communityId") Long communityId);
}
