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
                @Index(columnList = "articleId")
        }
)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Long articleId;

    @Column(length = 150)
    private String content;

    public Comment(UUID userId, Long articleId, String content) {
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
    }
}
