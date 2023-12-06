package hellowoori.backendproproject.domain.article.service.impl;

import hellowoori.backendproproject.domain.article.repository.ArticleRepository;
import hellowoori.backendproproject.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
}
