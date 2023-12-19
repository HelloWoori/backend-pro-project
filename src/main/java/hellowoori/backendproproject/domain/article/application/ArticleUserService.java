package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.article.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("articleUserService")
public class ArticleUserService implements ArticleBaseService {
    private final ArticleRepository articleRepository;

    public List<Article> findAllArticlesByGatheringId(Long gatheringId) {
        List<Article> articles = articleRepository.findByGatheringId(gatheringId);
        List<Long> articleIds = articles.stream()
                .map(Article::getArticleId)
                .collect(Collectors.toList());

        return articleRepository.findAllById(articleIds);
    }

    public Optional<Article> findOne(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }
}
