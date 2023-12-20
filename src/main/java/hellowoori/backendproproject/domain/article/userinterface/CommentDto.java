package hellowoori.backendproproject.domain.article.userinterface;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long commentId;
    private String nickname;
    private Long articleId;
    private String content;

    public CommentDto(Long commentId, String nickname, Long articleId, String content) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.articleId = articleId;
        this.content = content;
    }
}
