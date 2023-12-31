package hellowoori.backendproproject.domain.article.userinterface.controller;

import hellowoori.backendproproject.domain.article.application.ArticleService;
import hellowoori.backendproproject.domain.article.userinterface.dto.*;
import hellowoori.backendproproject.domain.dummy.application.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/communities")
public class ArticleCommunityController {

    private final ArticleService articleService;
    private final DummyService dummyService;

    @GetMapping("/{communityId}/articles")
    public String getArticles(@PathVariable Long communityId, Model model) {
        List<ArticleListDto> articleListDtoList = articleService.findAllArticlesByCommunityId(communityId);
        List<ArticleListResponse> articles = ArticleListResponse.of(articleListDtoList);
        model.addAttribute("communityId", communityId);
        model.addAttribute("articles", articles);
        return "article/articleList";
    }

    @GetMapping("/{communityId}/articles/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        ArticleDetailDto articleDetailDto = articleService.findOneArticle(articleId);
        ArticleDetailResponse articleDetail = ArticleDetailResponse.of(articleDetailDto);
        model.addAttribute("articleDetail", articleDetail);

        List<CommentListDto> commentListDtoList = articleService.findAllCommentsByArticleId(articleId);
        List<CommentListResponse> comments = CommentListResponse.of(commentListDtoList);
        model.addAttribute("comments", comments);
        return "article/articleDetail";
    }

    @GetMapping("/{communityId}/articles/add")
    public String showArticleAddFrom(@PathVariable Long communityId, Model model) {
        model.addAttribute("communityId", communityId);
        return "article/articleAddForm";
    }

    @PostMapping("/{communityId}/articles/add")
    public String createArticle(
            @PathVariable Long communityId,
            @RequestParam String imagePath,
            @RequestParam String content,
            @RequestParam(defaultValue = "false") boolean isCommentAllowed) {
        //TODO: userId를 로그인한 유저의 정보로 넣어줘야함
        ArticleAddRequest articleAddReq = new ArticleAddRequest(dummyService.getDummyUserId(), communityId, imagePath, content, isCommentAllowed);
        ArticleAddCommand articleAddCmd = articleAddReq.toCommand();
        articleService.saveArticle(articleAddCmd);
        return "redirect:/communities/{communityId}/articles";
    }

    @PostMapping("/{communityId}/articles/{articleId}/like")
    public String likeArticle(@PathVariable Long articleId, RedirectAttributes redirectAttributes) {
        //TODO: userId를 로그인한 유저의 정보로 넣어줘야함
        boolean resultLove = articleService.updateLove(articleId, dummyService.getDummyUserId());
        redirectAttributes.addAttribute("resultLove", resultLove);
        return "redirect:/communities/{communityId}/articles/{articleId}";
    }

    @PostMapping("/{communityId}/articles/{articleId}/comments/add")
    public String createComment(@PathVariable Long articleId, String content) {
        //TODO: userId를 로그인한 유저의 정보로 넣어줘야함
        CommentAddRequest commentAddReq = new CommentAddRequest(dummyService.getDummyUserId(), articleId, content);
        CommentAddCommand commentAddCmd = commentAddReq.toCommand();
        articleService.saveComment(commentAddCmd);
        return "redirect:/communities/{communityId}/articles/{articleId}";
    }


    @PostMapping("/{communityId}/articles/{articleId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        articleService.deleteComment(commentId, dummyService.getDummyUserId());
        return "redirect:/communities/{communityId}/articles/{articleId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        dummyService.makeDummyUserData();
        dummyService.makeDummyCommunityData();
        dummyService.makeDummyArticleData();
        dummyService.makeDummyCommentData();
    }
}
