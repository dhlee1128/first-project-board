package com.project.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.board.dto.UserAccountDto;
import com.project.board.dto.request.ArticleCommentRequest;
import com.project.board.dto.security.BoardPrincipal;
import com.project.board.service.ArticleCommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {
    
    private final ArticleCommentService articleCommentService;

    @PostMapping(value="/new")
    public String postNewArticleComment(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleCommentRequest articleCommentRequest
    ) {
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleCommentRequest.getArticleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long commentId, 
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            Long articleId) {
        articleCommentService.deleteArticleComment(commentId, boardPrincipal.getUsername());

        return "redirect:/articles/" + articleId;
    }
    

}
