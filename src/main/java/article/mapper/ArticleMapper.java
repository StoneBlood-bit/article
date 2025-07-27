package article.mapper;

import article.config.MapperConfig;
import article.dto.article.ArticleRequestDto;
import article.dto.article.ArticleResponseDto;
import article.model.Article;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ArticleMapper {
    ArticleResponseDto toDto(Article article);

    Article toEntity(ArticleRequestDto requestDto);
}
