package article.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleRequestDto {
    @NotBlank(message = "The field `title` cannot be empty")
    @Size(max = 100, message = "The title cannot contain more than 100 characters")
    private String title;

    @NotBlank(message = "The field `author` cannot be empty")
    private String author;

    @NotBlank(message = "The field `content` cannot be empty")
    private String content;
}
