package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentListResponse {

    private Long commentId;
    private Long articleId;
    private String nickname;
    private String content;

    public CommentListResponse(Long commentId, Long articleId, String nickname, String content) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.nickname = nickname;
        this.content = content;
    }

    public static List<CommentListResponse> of(List<CommentListDto> commentListDtoList) {
        return commentListDtoList.stream()
                .map(comment -> new CommentListResponse(
                        comment.getCommentId(),
                        comment.getArticleId(),
                        comment.getNickname(),
                        comment.getContent()))
                .collect(Collectors.toList());
    }
}
