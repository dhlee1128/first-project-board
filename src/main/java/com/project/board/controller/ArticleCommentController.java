package com.project.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.board.dto.UserAccountDto;
import com.project.board.dto.request.ArticleCommentRequest;
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
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        //TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
            "dhlee", "pw", "dhlee@mail.com", null, null
        )));

        return "redirect:/articles/" + articleCommentRequest.getArticleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }
    

}
