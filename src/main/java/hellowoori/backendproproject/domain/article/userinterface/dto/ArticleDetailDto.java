package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDetailDto {

    private Long articleId;
    private Long communityId;
    private String nickname;
    private String communityName;
    private String imagePath;
    private String content;
    private Long loveCount;

    public ArticleDetailDto(
            Long articleId,
            Long communityId,
            String nickname,
            String communityName,
            String imagePath,
            String content,
            Long loveCount) {
        this.articleId = articleId;
        this.communityId = communityId;
        this.nickname = nickname;
        this.communityName = communityName;
        this.imagePath = imagePath;
        this.content = content;
        this.loveCount = loveCount;
    }
}
