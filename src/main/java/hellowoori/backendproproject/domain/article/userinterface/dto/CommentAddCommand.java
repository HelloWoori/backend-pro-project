package hellowoori.backendproproject.domain.article.userinterface.dto;

import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.article.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentAddCommand {

    private UUID userId;
    private Long articleId;
    private String content;

    public CommentAddCommand(UUID userId, Long articleId, String content) {
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
    }

    public Comment toEntity(Article article) {
        return new Comment(
                this.getUserId(),
                this.getContent(),
                article
        );
    }
}
