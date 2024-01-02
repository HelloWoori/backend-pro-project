package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListDto {

    private Long articleId;
    private Long communityId;
    private String nickname;
    private String imagePath;

    public ArticleListDto(
            Long articleId,
            Long communityId,
            String nickname,
            String imagePath) {
        this.articleId = articleId;
        this.communityId = communityId;
        this.nickname = nickname;
        this.imagePath = imagePath;
    }
}
