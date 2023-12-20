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
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"articleId", "userId"})
        }
)
public class Love {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loveId;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private UUID userId;

    public Love(Long articleId, UUID userId) {
        this.articleId = articleId;
        this.userId = userId;
    }
}
