package hellowoori.backendproproject.domain.article.userinterface.dto;

import hellowoori.backendproproject.domain.article.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentAddCommand {

    private Long articleId;
    private String content;

    public CommentAddCommand(Long articleId, String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public Comment toEntity(UUID userId) {
        return new Comment(userId, this.getContent());
    }
}
