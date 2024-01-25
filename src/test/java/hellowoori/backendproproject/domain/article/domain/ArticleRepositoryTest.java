package hellowoori.backendproproject.domain.article.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LoveRepository loveRepository;

    @Test
    void equalsEntityTest1() {
        Optional<Article> article1 = articleRepository.findById(2L);
        Optional<Article> article2 = articleRepository.findById(2L);

        System.out.println(article1.toString());

        System.out.println(article1.get().getId());
        System.out.println(article2.get().getId());

        System.out.println(article1.get().getContent());
        System.out.println(article2.get().getContent());

        System.out.println(article1.get().getCreatedAt());
        System.out.println(article2.get().getCreatedAt());

        System.out.println(article1.get().hashCode());
        System.out.println(article2.get().hashCode());

        if(article1.get() == article2.get()) {
            System.out.println("== article1과 article2는 같아!");
        } else {
            System.out.println("== article1과 article2는 달라!");
        }

        if(article1.get().equals(article2.get())) {
            System.out.println("equals() article1과 article2는 같아!");
        } else {
            System.out.println("equals() article1과 article2는 달라!");
        }
    }
}