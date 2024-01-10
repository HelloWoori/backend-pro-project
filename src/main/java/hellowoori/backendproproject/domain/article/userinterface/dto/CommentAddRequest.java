package hellowoori.backendproproject.domain.article.userinterface.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentAddRequest {

    private Long articleId;
    private String content;

    public CommentAddRequest(Long articleId, String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public CommentAddCommand toCommand() {
        return new CommentAddCommand(this.articleId, this.content);
    }
}
