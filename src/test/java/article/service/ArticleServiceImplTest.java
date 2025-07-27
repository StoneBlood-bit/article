package article.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import article.dto.article.ArticleRequestDto;
import article.dto.article.ArticleResponseDto;
import article.mapper.ArticleMapper;
import article.model.Article;
import article.repository.ArticleRepository;
import article.service.article.ArticleServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    ArticleRepository articleRepository;

    @Mock
    ArticleMapper articleMapper;

    @Test
    @DisplayName("create article with valid data")
    void create_ValidArticleRequestDto_ShouldReturnArticleResponseDto() {
        ArticleRequestDto requestDto = new ArticleRequestDto();
        requestDto.setTitle("title");
        requestDto.setAuthor("author");
        requestDto.setContent("content");

        Article article = new Article();
        article.setTitle(requestDto.getTitle());
        article.setAuthor(requestDto.getAuthor());
        article.setContent(requestDto.getContent());
        article.setPublicationDate(LocalDateTime.now());

        ArticleResponseDto responseDto = new ArticleResponseDto();
        responseDto.setAuthor(article.getAuthor());
        responseDto.setContent(article.getContent());
        responseDto.setTitle(article.getTitle());
        responseDto.setPublicationDate(article.getPublicationDate());

        when(articleMapper.toEntity(requestDto)).thenReturn(article);
        when(articleRepository.save(article)).thenReturn(article);
        when(articleMapper.toDto(article)).thenReturn(responseDto);

        ArticleResponseDto savedArticle = articleService.create(requestDto);
        assertThat(savedArticle).isEqualTo(responseDto);
        verifyNoMoreInteractions(articleMapper, articleRepository);
    }

    @Test
    @DisplayName("Find all articles with valid Page")
    void findAll_ValidPageable_ShouldReturnPageOfArticleResponseDto() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("title");
        article.setAuthor("author");
        article.setContent("content");
        article.setPublicationDate(LocalDateTime.now());

        ArticleResponseDto responseDto = new ArticleResponseDto();
        responseDto.setId(article.getId());
        responseDto.setAuthor(article.getAuthor());
        responseDto.setContent(article.getContent());
        responseDto.setTitle(article.getTitle());
        responseDto.setPublicationDate(article.getPublicationDate());

        Pageable pageable = PageRequest.of(0, 5);
        List<Article> articles = List.of(article);
        Page<Article> articlePage = new PageImpl<>(articles, pageable, articles.size());

        when(articleRepository.findAll(pageable)).thenReturn(articlePage);
        when(articleMapper.toDto(article)).thenReturn(responseDto);

        Page<ArticleResponseDto> actualPage = articleService.findAll(pageable);

        assertThat(actualPage).hasSize(1);
        assertThat(actualPage.getContent().get(0)).isEqualTo(responseDto);
        verifyNoMoreInteractions(articleRepository, articleMapper);
    }
}
