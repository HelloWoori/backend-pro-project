package hellowoori.backendproproject.domain.article.userinterface.dto;

import hellowoori.backendproproject.domain.article.domain.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ArticleAddCommand {

    private UUID userId;
    private Long communityId;
    private String imagePath;
    private String content;
    private boolean isCommentAllowed;

    public ArticleAddCommand(
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

    public Article toEntity() {
        return new Article(
                this.getUserId(),
                this.getCommunityId(),
                this.getImagePath(),
                this.getContent(),
                this.isCommentAllowed());
    }
}
