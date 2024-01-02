package hellowoori.backendproproject.domain.article.domain;

import hellowoori.backendproproject.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(
        indexes = {
                @Index(columnList = "articleId"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"articleId", "userId"})
        }
)
public class Love extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "articleId")
    @ToString.Exclude
    private Article article;

    public Love(UUID userId, Article article) {
        this.userId = userId;
        this.article = article;
    }
}
