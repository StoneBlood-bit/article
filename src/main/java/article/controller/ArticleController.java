package article.controller;

import article.dto.article.ArticleRequestDto;
import article.dto.article.ArticleResponseDto;
import article.service.article.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponseDto create(@RequestBody @Valid ArticleRequestDto requestDto) {
        return articleService.create(requestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ArticleResponseDto> getAll(
            @PageableDefault(page = 0, size = 5, sort = "publicationDate")
            Pageable pageable
            ) {
        return articleService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/count-last-7-days")
    @ResponseStatus(HttpStatus.OK)
    public Long getArticlesCountLast7Days() {
        return articleService.countArticlesLast7Days();
    }
}
