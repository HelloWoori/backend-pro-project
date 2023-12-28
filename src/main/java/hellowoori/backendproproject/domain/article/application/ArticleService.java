package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.article.domain.*;
import hellowoori.backendproproject.domain.article.exception.ArticleNotFoundException;
import hellowoori.backendproproject.domain.article.exception.CommentNotFoundException;
import hellowoori.backendproproject.domain.article.userinterface.dto.*;
import hellowoori.backendproproject.domain.community.application.CommunityService;
import hellowoori.backendproproject.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final LoveRepository loveRepository;

    private final UserService userService;
    private final CommunityService communityService;

    public List<ArticleListDto> findAllArticlesByCommunityId(Long communityId) {
        List<Long> articleIds = articleRepository.findIdsByCommunityId(communityId);
        List<Article> articles = articleRepository.findAllById(articleIds);

        return articles.stream()
                .map(article -> new ArticleListDto(
                        article.getId(),
                        article.getCommunityId(),
                        userService.findNickname(article.getUserId()),
                        article.getImagePath()))
                .collect(Collectors.toList());
    }

    public ArticleDetailDto findOneArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        UUID userId = article.getUserId();
        Long communityId = article.getCommunityId();

        String nickname = userService.findNickname(userId);
        String communityName = communityService.findCommunityName(communityId);
        Long loveCount = this.countLove(articleId);

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
        List<Comment> comments = articleRepository.findById(articleId).get().getComments();
        return comments.stream()
                .map(comment -> new CommentListDto(
                        comment.getId(),
                        comment.getArticle().getId(),
                        userService.findNickname(comment.getUserId()),
                        comment.getContent()))
                .collect(Collectors.toList());
    }

    public void saveComment(CommentAddCommand commentAddCmd) {
        Article article = articleRepository.findById(commentAddCmd.getArticleId())
                .orElseThrow(() -> new ArticleNotFoundException(commentAddCmd.getArticleId()));
        commentRepository.save(commentAddCmd.toEntity(article));
    }

    public void deleteComment(Long commentId, UUID userId) {
        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new CommentNotFoundException(commentId, userId));
        System.out.println(comment.getContent());
        commentRepository.delete(comment);
    }

    public Long countLove(Long articleId) {
        return loveRepository.countByArticleId(articleId);
    }

    public boolean updateLove(Long articleId, UUID userId) {
        Love existingLove = loveRepository.findByArticleIdAndUserId(articleId, userId);

        if (existingLove != null) {
            //이미 행이 있는 경우, 좋아요 취소
            loveRepository.deleteById(existingLove.getId());
            return false;
        } else {
            //행이 없는 경우, 좋아요 추가
            Article article = articleRepository.findById(articleId)
                    .orElseThrow(() -> new ArticleNotFoundException(articleId));
            Love newLove = new Love(userId, article);
            loveRepository.save(newLove);
            return true;
        }
    }
}
