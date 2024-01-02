package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentDeleteRequest {

    private UUID userId;
    private Long articleId;
    private Long commentId;

    public CommentDeleteRequest(UUID userId, Long articleId, Long commentId) {
        this.userId = userId;
        this.articleId = articleId;
        this.commentId = commentId;
    }

    public CommentDeleteCommand toCommand() {
        return new CommentDeleteCommand(this.userId, this.articleId, this.commentId);
    }
}
