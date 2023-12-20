package hellowoori.backendproproject.domain.article.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "gatheringId")
        }
)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Long gatheringId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imagePath;

    @Column(length = 150)
    private String content;

    @Column(nullable = false)
    private boolean isCommentAllowed;

    @Column(columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private boolean isBlocked;

    public Article(UUID userId, Long gatheringId, String imagePath, String content, boolean isCommentAllowed) {
        this.userId = userId;
        this.gatheringId = gatheringId;
        this.imagePath = imagePath;
        this.content = content;
        this.isCommentAllowed = isCommentAllowed;
    }
}
