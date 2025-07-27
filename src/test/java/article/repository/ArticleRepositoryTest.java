package article.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import article.model.Article;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void countArticlesFromLast7Days_ShouldReturnCorrectCount() {
        Article a1 = new Article();
        a1.setTitle("article1");
        a1.setAuthor("author1");
        a1.setContent("content1");
        a1.setPublicationDate(LocalDateTime.now().minusDays(3));
        articleRepository.save(a1);

        Article a2 = new Article();
        a2.setTitle("article2");
        a2.setAuthor("author2");
        a2.setContent("content2");
        a2.setPublicationDate(LocalDateTime.now().minusDays(10));
        articleRepository.save(a2);

        Long count = articleRepository.countArticlesFromLast7Days(LocalDateTime.now().minusDays(7));
        assertEquals(1, count);
    }
}
