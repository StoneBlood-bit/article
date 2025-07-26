package article.dto.article;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String content;
}
