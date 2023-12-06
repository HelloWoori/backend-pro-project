package hellowoori.backendproproject.domain.article.entity;

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
                @Index(name = "idx_community_id", columnList = "communityId")
        }
)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long communityId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imagePath;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private boolean isCommentAllowed;

    @Builder
    public Article(Long userId, Long communityId, String imagePath, String content, boolean isCommentAllowed) {
        this.userId = userId;
        this.communityId = communityId;
        this.imagePath = imagePath;
        this.content = content;
        this.isCommentAllowed = isCommentAllowed;
    }
}
