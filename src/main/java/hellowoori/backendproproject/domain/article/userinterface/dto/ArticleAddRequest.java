package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ArticleAddRequest {

    private UUID userId;
    private Long communityId;
    private String imagePath;
    private String content;
    private boolean isCommentAllowed;

    public ArticleAddRequest(
            UUID userId,
            Long communityId,
            String imagePath,
            String content,
            boolean isCommentAllowed) {
        this.userId = userId;
        this.communityId = communityId;
        this.imagePath = imagePath;
        this.content = content;
        this.isCommentAllowed = isCommentAllowed;
    }

    public ArticleAddCommand toCommand() {
        return new ArticleAddCommand(this.userId, this.communityId, this.imagePath, this.content, this.isCommentAllowed);
    }
}
