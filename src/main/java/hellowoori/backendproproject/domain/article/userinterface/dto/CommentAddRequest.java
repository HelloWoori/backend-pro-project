package hellowoori.backendproproject.domain.article.userinterface.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentAddRequest {

    private UUID userId;
    private Long articleId;
    private String content;

    public CommentAddRequest(UUID userId, Long articleId, String content) {
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
    }

    public CommentAddCommand toCommand() {
        return new CommentAddCommand(this.userId, this.articleId, this.content);
    }
}
