package hellowoori.backendproproject.domain.article.domain;

import hellowoori.backendproproject.domain.article.exception.CommentNotFoundException;
import hellowoori.backendproproject.global.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = true)
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

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setArticle(this);
    }

    public void removeComment(UUID userId, Long commentId) {
        Comment commentToDelete = validateComment(userId, commentId);
        comments.remove(commentToDelete);
        commentToDelete.setArticle(null);
    }

    public Optional<Comment> findCommentByIdAndUserId(Long commentId, UUID userId) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(commentId) && comment.getUserId().equals(userId))
                .findFirst();
    }


    public Optional<Love> findLoveByUserId(UUID userId) {
        return loves.stream()
                .filter(love -> love.getUserId().equals(userId))
                .findFirst();
    }

    public int getLoveCount() {
        return loves.size();
    }

    public boolean changeLove(UUID userId) {
        Optional<Love> existingLove = findLoveByUserId(userId);
        if (existingLove.isPresent()) {
            removeLove(existingLove.get());
            return false;
        } else {
            Love newLove = new Love(userId, this);
            addLove(newLove);
            return true;
        }
    }

    private void addLove(Love love) {
        loves.add(love);
        love.setArticle(this);
    }

    private void removeLove(Love love) {
        loves.remove(love);
        love.setArticle(null);
    }

    private Comment validateComment(UUID userId, Long commentId) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundException(commentId, userId));
    }
}
