package article.service.article;

import article.dto.article.ArticleRequestDto;
import article.dto.article.ArticleResponseDto;
import article.mapper.ArticleMapper;
import article.model.Article;
import article.repository.ArticleRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private static final int COUNT_OF_DAYS = 7;
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDto create(ArticleRequestDto requestDto) {
        Article article = articleMapper.toEntity(requestDto);
        article.setPublicationDate(LocalDateTime.now());
        return articleMapper.toDto(articleRepository.save(article));
    }

    @Override
    public Page<ArticleResponseDto> findAll(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(articleMapper::toDto);
    }

    @Override
    public Long countArticlesLast7Days() {
        return articleRepository.countArticlesFromLast7Days(
                LocalDateTime.now().minusDays(COUNT_OF_DAYS)
        );
    }
}
