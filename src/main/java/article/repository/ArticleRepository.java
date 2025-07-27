package article.repository;

import article.model.Article;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT COUNT(a) FROM Article a WHERE a.publicationDate >= :startDate")
    Long countArticlesFromLast7Days(@Param("startDate") LocalDateTime startDate);
}
