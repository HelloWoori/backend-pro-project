package hellowoori.backendproproject.domain.article.repository;

import hellowoori.backendproproject.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
