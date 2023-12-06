package hellowoori.backendproproject.domain.community.service;

import hellowoori.backendproproject.domain.article.entity.Article;

import java.util.List;

public interface CommunityService {
    public List<Article> getArticlesByCommunityId(Long communityId);
    public void makeDummyData();
}
