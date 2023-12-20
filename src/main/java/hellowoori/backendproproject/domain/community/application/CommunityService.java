package hellowoori.backendproproject.domain.community.application;

import hellowoori.backendproproject.domain.article.application.ArticleBaseService;
import hellowoori.backendproproject.domain.article.application.CommentService;
import hellowoori.backendproproject.domain.article.application.LoveService;
import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.article.domain.Comment;
import hellowoori.backendproproject.domain.article.userinterface.ArticleDetailDto;
import hellowoori.backendproproject.domain.article.userinterface.ArticleListDto;
import hellowoori.backendproproject.domain.article.userinterface.CommentDto;
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
    private final LoveService loveService;
    private final CommentService commentService;

    public CommunityService(
            UserService userService,
            @Qualifier("articleCommunityService") ArticleBaseService articleUserService,
            GatheringService gatheringService,
            LoveService loveService,
            CommentService commentService) {
        this.userService = userService;
        this.articleBaseService = articleUserService;
        this.gatheringService = gatheringService;
        this.loveService = loveService;
        this.commentService = commentService;
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
            Long loveCount = loveService.countLove(articleId);

            return new ArticleDetailDto(
                    articleId,
                    gatheringId,
                    nickname,
                    gatheringName,
                    article.getImagePath(),
                    article.getContent(),
                    loveCount);
        }

        return null;
    }

    public void saveArticle(Article article) {
        articleBaseService.save(article);
    }

    public boolean updateLove(Long articleId, UUID userId) {
        return loveService.switchLoveStatus(articleId, userId);
    }

    public void addComment() {

    }

    public List<CommentDto> getComments(Long articleId) {
        List<Comment> comments = commentService.findAllCommentsByArticleId(articleId);

        return comments.stream()
                .map(comment -> new CommentDto(
                        comment.getCommentId(),
                        userService.findNickname(comment.getUserId()),
                        comment.getArticleId(),
                        comment.getContent()))
                .collect(Collectors.toList());
    }
}
