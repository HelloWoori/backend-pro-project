package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteCommand {

    private Long articleId;
    private Long commentId;

    public CommentDeleteCommand(Long articleId, Long commentId) {
        this.articleId = articleId;
        this.commentId = commentId;
    }
}
