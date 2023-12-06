package hellowoori.backendproproject.domain.community.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        indexes = {
                @Index(name = "idx_user_id", columnList = "userId"),
                @Index(name = "idx_article_id", columnList = "articleId")

        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_community_user_article", columnNames = {"communityId", "userId", "articleId"})
        }

)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long communityId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long articleId;

    @Builder
    public Board(Long communityId, Long userId, Long articleId) {
        this.communityId = communityId;
        this.userId = userId;
        this.articleId = articleId;
    }
}
