package hellowoori.backendproproject.global.error;

import hellowoori.backendproproject.domain.article.exception.ArticleNotFoundException;
import hellowoori.backendproproject.domain.article.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handlerArticleNotFoundException(ArticleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handlerCommentNotFoundException(CommentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
