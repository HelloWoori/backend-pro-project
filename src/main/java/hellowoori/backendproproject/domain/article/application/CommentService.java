package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.article.domain.Comment;
import hellowoori.backendproproject.domain.article.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findAllCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}
