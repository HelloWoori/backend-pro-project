package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDetailResponse {

    private Long articleId;
    private Long communityId;
    private String nickname;
    private String communityName;
    private String imagePath;
    private String content;
    private int loveCount;

    public ArticleDetailResponse(
            Long articleId,
            Long communityId,
            String nickname,
            String communityName,
            String imagePath,
            String content,
            int loveCount) {
        this.articleId = articleId;
        this.communityId = communityId;
        this.nickname = nickname;
        this.communityName = communityName;
        this.imagePath = imagePath;
        this.content = content;
        this.loveCount = loveCount;
    }

    public static ArticleDetailResponse of(ArticleDetailDto articleDetailDto) {
        return new ArticleDetailResponse(
                articleDetailDto.getArticleId(),
                articleDetailDto.getCommunityId(),
                articleDetailDto.getNickname(),
                articleDetailDto.getCommunityName(),
                articleDetailDto.getImagePath(),
                articleDetailDto.getContent(),
                articleDetailDto.getLoveCount()
        );
    }
}
