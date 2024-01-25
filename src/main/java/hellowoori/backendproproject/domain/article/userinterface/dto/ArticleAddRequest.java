package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleAddRequest {

    private Long communityId;
    private String imagePath;
    private String content;
    private boolean isCommentAllowed;

    public ArticleAddRequest(
            Long communityId,
            String imagePath,
            String content,
            boolean isCommentAllowed) {
        this.communityId = communityId;
        this.imagePath = imagePath;
        this.content = content;
        this.isCommentAllowed = isCommentAllowed;
    }

    public ArticleAddCommand toCommand() {
        return new ArticleAddCommand(this.communityId, this.imagePath, this.content, this.isCommentAllowed);
    }
}
