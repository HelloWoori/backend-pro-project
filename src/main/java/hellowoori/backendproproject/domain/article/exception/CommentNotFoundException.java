package hellowoori.backendproproject.domain.article.exception;

import hellowoori.backendproproject.global.error.BaseRuntimeException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentNotFoundException extends BaseRuntimeException {

    public CommentNotFoundException(Long commentId, UUID userId) {
        super("Comment not found with commentId: " + commentId + ", userId: " + userId);
    }
}
