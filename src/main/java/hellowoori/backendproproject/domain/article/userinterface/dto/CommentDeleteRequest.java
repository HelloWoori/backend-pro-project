package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteRequest {

    private Long articleId;
    private Long commentId;

    public CommentDeleteRequest(Long articleId, Long commentId) {
        this.articleId = articleId;
        this.commentId = commentId;
    }

    public CommentDeleteCommand toCommand() {
        return new CommentDeleteCommand(this.articleId, this.commentId);
    }
}
