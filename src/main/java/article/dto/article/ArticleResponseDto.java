package article.dto.article;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime publicationDate;
}
