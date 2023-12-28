package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListDto {

    private Long commentId;
    private Long articleId;
    private String nickname;
    private String content;

    public CommentListDto(Long commentId, Long articleId, String nickname, String content) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.nickname = nickname;
        this.content = content;
    }
}
