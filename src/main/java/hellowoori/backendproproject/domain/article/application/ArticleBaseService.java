package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleBaseService {
    List<Article> findAllArticlesByGatheringId(Long gatheringId);
    Optional<Article> findOne(Long articleId);
    void save(Article article);
}
