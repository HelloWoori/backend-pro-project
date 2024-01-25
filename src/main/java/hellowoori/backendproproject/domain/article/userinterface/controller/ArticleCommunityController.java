package hellowoori.backendproproject.domain.article.userinterface.controller;

import hellowoori.backendproproject.domain.article.application.ArticleService;
import hellowoori.backendproproject.domain.article.userinterface.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/communities/{communityId}/articles")
public class ArticleCommunityController {

    private final ArticleService articleService;

    @GetMapping
    public String getArticles(@PathVariable Long communityId, Model model) {
        List<ArticleListDto> articleListDtoList = articleService.findAllArticlesByCommunityId(communityId);
        List<ArticleListResponse> articles = ArticleListResponse.of(articleListDtoList);
        model.addAttribute("communityId", communityId);
        model.addAttribute("articles", articles);
        return "article/articleList";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        ArticleDetailDto articleDetailDto = articleService.findOneArticle(articleId);
        ArticleDetailResponse articleDetail = ArticleDetailResponse.of(articleDetailDto);
        model.addAttribute("articleDetail", articleDetail);

        List<CommentListDto> commentListDtoList = articleService.findAllCommentsByArticleId(articleId);
        List<CommentListResponse> comments = CommentListResponse.of(commentListDtoList);
        model.addAttribute("comments", comments);
        return "article/articleDetail";
    }

    @GetMapping("/add")
    public String showArticleAddFrom(@PathVariable Long communityId, Model model) {
        model.addAttribute("communityId", communityId);
        return "article/articleAddForm";
    }

    @PostMapping("/add")
    public String createArticle(
            @PathVariable Long communityId,
            @RequestParam String imagePath,
            @RequestParam String content,
            @RequestParam(defaultValue = "false") boolean isCommentAllowed) {
        ArticleAddRequest articleAddReq = new ArticleAddRequest(communityId, imagePath, content, isCommentAllowed);
        ArticleAddCommand articleAddCmd = articleAddReq.toCommand();
        articleService.saveArticle(articleAddCmd);
        return "redirect:/communities/{communityId}/articles";
    }

    @PostMapping("/{articleId}/like")
    public String likeArticle(@PathVariable Long articleId, RedirectAttributes redirectAttributes) {
        boolean resultLove = articleService.toggleLove(articleId);
        redirectAttributes.addAttribute("resultLove", resultLove);
        return "redirect:/communities/{communityId}/articles/{articleId}";
    }

    @PostMapping("/{articleId}/comments/add")
    public String createComment(@PathVariable Long articleId, String content) {
        CommentAddRequest commentAddReq = new CommentAddRequest(articleId, content);
        CommentAddCommand commentAddCmd = commentAddReq.toCommand();
        articleService.saveComment(commentAddCmd);
        return "redirect:/communities/{communityId}/articles/{articleId}";
    }


    @PostMapping("/{articleId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        CommentDeleteRequest commentDeleteReq = new CommentDeleteRequest(articleId, commentId);
        CommentDeleteCommand commentDeleteCmd = commentDeleteReq.toCommand();
        articleService.deleteComment(commentDeleteCmd);
        return "redirect:/communities/{communityId}/articles/{articleId}";
    }
}
