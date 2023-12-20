package hellowoori.backendproproject.domain.article.userinterface;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDetailDto {

    private Long articleId;
    private Long gatheringId;
    private String nickname;
    private String gatheringName;
    private String imagePath;
    private String content;
    private Long loveCount;

    public ArticleDetailDto(
            Long articleId,
            Long gatheringId,
            String nickname,
            String gatheringName,
            String imagePath,
            String content,
            Long loveCount) {
        this.articleId = articleId;
        this.gatheringId = gatheringId;
        this.nickname = nickname;
        this.gatheringName = gatheringName;
        this.imagePath = imagePath;
        this.content = content;
        this.loveCount = loveCount;
    }
}
