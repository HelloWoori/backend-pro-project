package hellowoori.backendproproject.domain.article.domain;

import hellowoori.backendproproject.global.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "articleId")
        }
)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID userId;

    @Column(length = 150)
    private String content;

    @ManyToOne
    @JoinColumn(name = "articleId")
    @ToString.Exclude
    private Article article;

    public Comment(UUID userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
