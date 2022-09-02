package com.project.board.dto.request;

import com.project.board.dto.ArticleCommentDto;
import com.project.board.dto.UserAccountDto;

public class ArticleCommentRequest {
    private Long articleId;
    private String content;


    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public ArticleCommentRequest(Long articleId, String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
            articleId, 
            userAccountDto, 
            content
        );
    }

    
}
