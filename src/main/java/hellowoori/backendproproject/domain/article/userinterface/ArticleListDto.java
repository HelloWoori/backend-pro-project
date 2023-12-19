package hellowoori.backendproproject.domain.article.userinterface;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListDto {

    private Long articleId;
    private Long gatheringId;
    private String nickname;
    private String imagePath;

    public ArticleListDto(
            Long articleId,
            Long gatheringId,
            String nickname,
            String imagePath) {
        this.articleId = articleId;
        this.gatheringId = gatheringId;
        this.nickname = nickname;
        this.imagePath = imagePath;
    }
}
