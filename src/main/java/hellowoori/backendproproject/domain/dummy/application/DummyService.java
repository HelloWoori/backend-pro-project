package hellowoori.backendproproject.domain.dummy.application;

import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.gathering.domain.Gathering;
import hellowoori.backendproproject.domain.article.domain.ArticleRepository;
import hellowoori.backendproproject.domain.gathering.domain.GatheringRepository;
import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DummyService {

    private final UserRepository userRepository;
    private final GatheringRepository gatheringRepository;
    private final ArticleRepository articleRepository;

    public void makeDummyUserData() {
        userRepository.save(new User("dummy01"));
        userRepository.save(new User("dummy02"));
        userRepository.save(new User("dummy03"));
    }

    public void makeDummyGatheringData() {
        gatheringRepository.save(new Gathering("모임1", ""));
        gatheringRepository.save(new Gathering("모임2", "222"));
        gatheringRepository.save(new Gathering("모임3", "333"));
    }

    public UUID getDummyUserId() {
        List<User> users = userRepository.findAll();
        if (users.size() < 1) {
            return UUID.randomUUID();
        }

        return users.get(0).getUserId();
    }

    public void makeDummyArticleData() {
        List<User> users = userRepository.findAll();
        if (users.size() < 3) {
            return;
        }

        // 인증글 정보 생성 + 게시판에도 등록
        articleRepository.save(new Article(
                users.get(0).getUserId(),
                1L,
                "/images/default.png",
                "예제1",
                true));

        articleRepository.save(new Article(
                users.get(0).getUserId(),
                1L,
                "/images/default.png",
                "예제2",
                false));

        articleRepository.save(new Article(
                users.get(0).getUserId(),
                2L,
                "/images/default.png",
                "예제3",
                true));

        articleRepository.save(new Article(
                users.get(1).getUserId(),
                1L,
                "/images/default.png",
                "예제4",
                true));
        articleRepository.save(new Article(
                users.get(2).getUserId(),
                3L,
                "/images/default.png",
                "예제5",
                true));
    }
}
