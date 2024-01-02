package hellowoori.backendproproject.domain.article.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long commentId, UUID userId) {
        super("Comment not found with commentId: " + commentId + ", userId: " + userId);
    }
}
