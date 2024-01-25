package hellowoori.backendproproject.domain.article.userinterface.dto;

import hellowoori.backendproproject.domain.article.domain.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ArticleAddCommand {

    private Long communityId;
    private String imagePath;
    private String content;
    private boolean isCommentAllowed;

    public ArticleAddCommand(
            Long communityId,
            String imagePath,
            String content,
            boolean isCommentAllowed) {
        this.communityId = communityId;
        this.imagePath = imagePath;
        this.content = content;
        this.isCommentAllowed = isCommentAllowed;
    }

    public Article toEntity(UUID userId) {
        return new Article(
                userId,
                this.getCommunityId(),
                this.getImagePath(),
                this.getContent(),
                this.isCommentAllowed());
    }
}
