package hellowoori.backendproproject.domain.article.userinterface.dto;

import hellowoori.backendproproject.domain.article.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentDeleteCommand {

    private UUID userId;
    private Long articleId;
    private Long commentId;

    public CommentDeleteCommand(UUID userId, Long articleId, Long commentId) {
        this.userId = userId;
        this.articleId = articleId;
        this.commentId = commentId;
    }
}
