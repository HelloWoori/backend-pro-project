package hellowoori.backendproproject.domain.community.userinterface;

import hellowoori.backendproproject.domain.article.userinterface.ArticleDetailDto;
import hellowoori.backendproproject.domain.article.userinterface.ArticleListDto;
import hellowoori.backendproproject.domain.community.application.CommunityService;
import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.dummy.application.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final DummyService dummyService;

    @GetMapping("/{gatheringId}/articles")
    public String articles(@PathVariable Long gatheringId, Model model) {
        System.out.println(gatheringId);
        List<ArticleListDto> articles = communityService.getArticles(gatheringId);
        model.addAttribute("gatheringId", gatheringId);
        model.addAttribute("articles", articles);
        return "community/articles";
    }

    @GetMapping("/{gatheringId}/articles/{articleId}")
    public String article(@PathVariable Long articleId, Model model) {
        ArticleDetailDto articleDetail = communityService.getArticleDetail(articleId);
        if (articleDetail == null) {
            return "error/404";
        }
        model.addAttribute("articleDetail", articleDetail);
        return "community/article";
    }

    @GetMapping("/{gatheringId}/articles/add")
    public String addForm(@PathVariable Long gatheringId, Model model) {
        model.addAttribute("gatheringId", gatheringId);
        return "community/formArticleAdd";
    }

    @PostMapping("/{gatheringId}/articles/add")
    public String addArticle(@PathVariable Long gatheringId,
                             @RequestParam String imagePath,
                             @RequestParam String content,
                             @RequestParam(defaultValue = "false") boolean isCommentAllowed) {
        // TODO: userId를 로그인한 유저의 정보로 넣어줘야함
        Article article = new Article(dummyService.getDummyUserId(), gatheringId, imagePath, content, isCommentAllowed);
        communityService.saveArticle(article);
        return "redirect:/community/{gatheringId}/articles";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        dummyService.makeDummyUserData();
        dummyService.makeDummyGatheringData();
        dummyService.makeDummyArticleData();
    }
}
