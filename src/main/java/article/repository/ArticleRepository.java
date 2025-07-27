package article.repository;

import article.model.Article;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Long countByPublicationDateBetween(LocalDateTime now, LocalDateTime end);
}
