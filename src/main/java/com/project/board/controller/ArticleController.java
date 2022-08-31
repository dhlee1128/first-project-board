package com.project.board.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.board.domain.type.SearchType;
import com.project.board.dto.response.ArticleResponse;
import com.project.board.dto.response.ArticleWithCommentResponse;
import com.project.board.service.ArticleService;
import com.project.board.service.PaginationService;

import lombok.RequiredArgsConstructor;

/**
 * /articles
 * /articles/{article-id}
 * /articles/search
 * /articles/search-hashtag
 */

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable Pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, Pageable).map(ArticleResponse::from);
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(Pageable.getPageNumber(), articles.getTotalPages());
        
        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumber);
        map.addAttribute("searchTypes", SearchType.values());

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String articles(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentResponse article = ArticleWithCommentResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.getArticleCommentResponses());

        return "articles/detail";
    }
    
}
