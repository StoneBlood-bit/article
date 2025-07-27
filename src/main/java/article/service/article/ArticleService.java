package article.service.article;

import article.dto.article.ArticleRequestDto;
import article.dto.article.ArticleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    ArticleResponseDto create(ArticleRequestDto requestDto);

    Page<ArticleResponseDto> findAll(Pageable pageable);

    Long countArticlesLast7Days();
}
