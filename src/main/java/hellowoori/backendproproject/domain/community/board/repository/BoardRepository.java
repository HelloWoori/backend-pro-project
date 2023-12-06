package hellowoori.backendproproject.domain.community.board.repository;

import hellowoori.backendproproject.domain.community.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByCommunityId(Long communityId);
}
