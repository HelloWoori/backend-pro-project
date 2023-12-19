package hellowoori.backendproproject.domain.community.application;

import hellowoori.backendproproject.domain.article.application.ArticleBaseService;
import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.article.userinterface.ArticleDetailDto;
import hellowoori.backendproproject.domain.article.userinterface.ArticleListDto;
import hellowoori.backendproproject.domain.gathering.application.GatheringService;
import hellowoori.backendproproject.domain.user.application.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommunityService {

    private final UserService userService;
    private final ArticleBaseService articleBaseService;
    private final GatheringService gatheringService;

    public CommunityService(
            UserService userService,
            @Qualifier("articleCommunityService") ArticleBaseService articleUserService,
            GatheringService gatheringService) {
        this.userService = userService;
        this.articleBaseService = articleUserService;
        this.gatheringService = gatheringService;
    }

    public List<ArticleListDto> getArticles(Long gatheringId) {
        List<Article> articles = articleBaseService.findAllArticlesByGatheringId(gatheringId);

        return articles.stream()
                .map(article -> new ArticleListDto(
                        article.getArticleId(),
                        article.getGatheringId(),
                        userService.findNickname(article.getUserId()),
                        article.getImagePath()))
                .collect(Collectors.toList());
    }

    public ArticleDetailDto getArticleDetail(Long articleId) {
        Optional<Article> optionalArticle = articleBaseService.findOne(articleId);
        if (optionalArticle.isPresent())
        {
            Article article = optionalArticle.get();
            UUID userId = article.getUserId();
            Long gatheringId = article.getGatheringId();

            String nickname = userService.findNickname(userId);
            String gatheringName = gatheringService.findGatheringName(gatheringId);

            return new ArticleDetailDto(
                    articleId,
                    gatheringId,
                    nickname,
                    gatheringName,
                    article.getImagePath(),
                    article.getContent());
        }

        return null;
    }

    public void saveArticle(Article article) {
        articleBaseService.save(article);
    }
}
