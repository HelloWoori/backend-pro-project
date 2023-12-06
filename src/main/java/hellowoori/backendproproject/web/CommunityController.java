package hellowoori.backendproproject.web;

import hellowoori.backendproproject.domain.article.entity.Article;
import hellowoori.backendproproject.domain.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("/{communityId}/articles")
    public String articles(@PathVariable Long communityId, Model model) {
        System.out.println(communityId);
        List<Article> articles = communityService.getArticlesByCommunityId(communityId);
        model.addAttribute("articles", articles);
        return "/community/articles";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        communityService.makeDummyData();
    }
}
