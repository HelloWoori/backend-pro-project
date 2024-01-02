package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.article.domain.*;
import hellowoori.backendproproject.domain.article.exception.ArticleNotFoundException;
import hellowoori.backendproproject.domain.article.exception.CommentNotFoundException;
import hellowoori.backendproproject.domain.article.userinterface.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserServiceClient userServiceClient;
    private final CommunityServiceClient communityServiceClient;

    @Transactional(readOnly = true)
    public List<ArticleListDto> findAllArticlesByCommunityId(Long communityId) {
        List<Article> articles = articleRepository.findAllByCommunityId(communityId);

        return articles.stream()
                .map(article -> new ArticleListDto(
                        article.getId(),
                        article.getCommunityId(),
                        userServiceClient.findNickname(article.getUserId()),
                        article.getImagePath()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleDetailDto findOneArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        UUID userId = article.getUserId();
        Long communityId = article.getCommunityId();

        String nickname = userServiceClient.findNickname(userId);
        String communityName = communityServiceClient.findCommunityName(communityId);
        int loveCount = article.getLoveCount();

        return new ArticleDetailDto(
                articleId,
                communityId,
                nickname,
                communityName,
                article.getImagePath(),
                article.getContent(),
                loveCount);
    }

    public void saveArticle(ArticleAddCommand articleAddCmd) {
        articleRepository.save(articleAddCmd.toEntity());
    }

    public List<CommentListDto> findAllCommentsByArticleId(Long articleId) {
        List<Comment> comments = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId)).getComments();
        return comments.stream()
                .map(comment -> new CommentListDto(
                        comment.getId(),
                        comment.getArticle().getId(),
                        userServiceClient.findNickname(comment.getUserId()),
                        comment.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveComment(CommentAddCommand commentAddCmd) {
        Article article = articleRepository.findById(commentAddCmd.getArticleId())
                .orElseThrow(() -> new ArticleNotFoundException(commentAddCmd.getArticleId()));
        article.addComment(commentAddCmd.toEntity());
    }

    @Transactional
    public void deleteComment(CommentDeleteCommand commentDeleteCmd) {
        Article article = articleRepository.findById(commentDeleteCmd.getArticleId())
                .orElseThrow(() -> new ArticleNotFoundException(commentDeleteCmd.getArticleId()));
        Comment comment = article.findCommentByIdAndUserId(commentDeleteCmd.getCommentId(), commentDeleteCmd.getUserId())
                .orElseThrow(() -> new CommentNotFoundException(commentDeleteCmd.getCommentId(), commentDeleteCmd.getUserId()));
        article.removeComment(comment);
    }

    @Transactional
    public boolean toggleLove(Long articleId, UUID userId) {
        Article article = articleRepository.findByIdAndUserId(articleId, userId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));
        Optional<Love> existingLove = article.findLoveByUserId(userId);
        if (existingLove.isPresent()) {
            article.removeLove(existingLove.get());
            return false;
        } else {
            Love newLove = new Love(userId, article);
            article.addLove(newLove);
            return true;
        }
    }
}
