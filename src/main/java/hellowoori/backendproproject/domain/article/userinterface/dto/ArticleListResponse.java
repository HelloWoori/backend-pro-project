package hellowoori.backendproproject.domain.article.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArticleListResponse {

    private Long articleId;
    private Long communityId;
    private String nickname;
    private String imagePath;

    public ArticleListResponse(
            Long articleId,
            Long communityId,
            String nickname,
            String imagePath) {
        this.articleId = articleId;
        this.communityId = communityId;
        this.nickname = nickname;
        this.imagePath = imagePath;
    }

    public static List<ArticleListResponse> of(List<ArticleListDto> articleListDtoList) {
        return articleListDtoList.stream()
                .map(article -> new ArticleListResponse(
                        article.getArticleId(),
                        article.getCommunityId(),
                        article.getNickname(),
                        article.getImagePath()))
                .collect(Collectors.toList());
    }
}
