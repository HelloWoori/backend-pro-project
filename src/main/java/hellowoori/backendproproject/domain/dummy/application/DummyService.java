package hellowoori.backendproproject.domain.dummy.application;

import hellowoori.backendproproject.domain.article.domain.Article;
import hellowoori.backendproproject.domain.community.domain.Community;
import hellowoori.backendproproject.domain.article.domain.ArticleRepository;
import hellowoori.backendproproject.domain.community.domain.CommunityRepository;
import hellowoori.backendproproject.domain.community.domain.ECommunityJoinStatus;
import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyService {

    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final ArticleRepository articleRepository;


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        this.makeDummyUserData();
        this.makeDummyCommunityData();
        this.makeDummyArticleData();
        this.makeDummyCommentData();
    }

    public void makeDummyUserData() {
        userRepository.save(new User("a@gamil.com", "dummy01", "1234"));
        userRepository.save(new User("b@gamil.com", "dummy02", "4567"));
        userRepository.save(new User("c@gamil.com", "dummy03", "8910"));
    }

    public void makeDummyCommunityData() {
        communityRepository.save(new Community("모임1", "", ECommunityJoinStatus.JOIN_FREE));
        communityRepository.save(new Community("모임2", "222", ECommunityJoinStatus.JOIN_FREE));
        communityRepository.save(new Community("모임3", "333", ECommunityJoinStatus.JOIN_APPLY));
    }

    public void makeDummyCommentData() {
    }

    public User getDummyUser() {
        List<User> users = userRepository.findAll();
        if (users.size() < 1) {
            throw new RuntimeException("Dummy user error");
        }
        return users.get(0);
    }

    public void makeDummyArticleData() {
        List<User> users = userRepository.findAll();
        if (users.size() < 3) {
            return;
        }

        // 인증글 정보 생성 + 게시판에도 등록
        articleRepository.save(new Article(
                users.get(0).getId(),
                1L,
                "/images/default.png",
                "예제1",
                true));

        articleRepository.save(new Article(
                users.get(0).getId(),
                1L,
                "/images/default.png",
                "예제2",
                false));

        articleRepository.save(new Article(
                users.get(0).getId(),
                2L,
                "/images/default.png",
                "예제3",
                true));

        articleRepository.save(new Article(
                users.get(1).getId(),
                1L,
                "/images/default.png",
                "예제4",
                true));
        articleRepository.save(new Article(
                users.get(2).getId(),
                3L,
                "/images/default.png",
                "예제5",
                true));
    }
}
