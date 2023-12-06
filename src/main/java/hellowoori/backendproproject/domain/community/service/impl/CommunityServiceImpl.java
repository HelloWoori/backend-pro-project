package hellowoori.backendproproject.domain.community.service.impl;

import hellowoori.backendproproject.domain.article.entity.Article;
import hellowoori.backendproproject.domain.article.repository.ArticleRepository;
import hellowoori.backendproproject.domain.community.board.entity.Board;
import hellowoori.backendproproject.domain.community.board.repository.BoardRepository;
import hellowoori.backendproproject.domain.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<Article> getArticlesByCommunityId(Long communityId) {
        List<Board> boards = boardRepository.findByCommunityId(communityId);
        List<Long> articleIds = boards.stream()
                .map(Board::getArticleId)
                .collect(Collectors.toList());

        return articleRepository.findAllById(articleIds);
    }

    /**
     * TODO: 삭제 예정
     * 임시 데이터 생성
     */
    @Override
    public void makeDummyData()
    {
        // 인증글 정보 생성 + 게시판에도 등록
        articleRepository.save(new Article(
                123L,
                1000L,
                "/images/default.png",
                "예제1",
                true));

        boardRepository.save(new Board(1000L, 123L, 1L));

        articleRepository.save(new Article(
                123L,
                1000L,
                "/images/default.png",
                "예제2",
                false));

        boardRepository.save(new Board(1000L, 123L, 2L));

        articleRepository.save(new Article(
                123L,
                2000L,
                "/images/default.png",
                "예제3",
                true));

        boardRepository.save(new Board(2000L, 123L, 3L));

        articleRepository.save(new Article(
                456L,
                1000L,
                "/images/default.png",
                "예제4",
                true));

        boardRepository.save(new Board(1000L, 456L, 4L));
    }
}
