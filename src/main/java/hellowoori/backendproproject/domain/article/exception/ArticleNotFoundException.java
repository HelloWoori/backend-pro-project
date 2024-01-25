package hellowoori.backendproproject.domain.article.exception;

import hellowoori.backendproproject.global.error.BaseRuntimeException;
import lombok.Getter;

@Getter
public class ArticleNotFoundException extends BaseRuntimeException {

    public ArticleNotFoundException(Long articleId) {
        super("Article not found with id: " + articleId);
    }
}
