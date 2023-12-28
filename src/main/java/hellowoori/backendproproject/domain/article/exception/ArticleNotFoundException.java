package hellowoori.backendproproject.domain.article.exception;

import lombok.Getter;

@Getter
public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long articleId) {
        super("Article not found with id: " + articleId);
    }
}
