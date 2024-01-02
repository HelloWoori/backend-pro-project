package hellowoori.backendproproject.domain.article.domain;

import hellowoori.backendproproject.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "communityId")
        }
)
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Long communityId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imagePath;

    @Column(length = 150)
    private String content;

    @Column(nullable = false)
    private boolean isCommentAllowed;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Love> loves = new ArrayList<>();

    public Article(UUID userId, Long communityId, String imagePath, String content, boolean isCommentAllowed) {
        this.userId = userId;
        this.communityId = communityId;
        this.imagePath = imagePath;
        this.content = content;
        this.isCommentAllowed = isCommentAllowed;
    }
}
