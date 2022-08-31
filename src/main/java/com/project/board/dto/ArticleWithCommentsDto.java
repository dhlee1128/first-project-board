package com.project.board.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class ArticleWithCommentsDto {
    private Long id;
    private UserAccountDto userAccountDto;
    private Set<ArticleCommentDto> articleCommentDtos;
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccountDto getUserAccountDto() {
        return this.userAccountDto;
    }

    public void setUserAccountDto(UserAccountDto userAccountDto) {
        this.userAccountDto = userAccountDto;
    }

    public Set<ArticleCommentDto> getArticleCommentDtos() {
        return this.articleCommentDtos;
    }

    public void setArticleCommentDtos(Set<ArticleCommentDto> articleCommentDtos) {
        this.articleCommentDtos = articleCommentDtos;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashtag() {
        return this.hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    public ArticleWithCommentsDto(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos, String title, String content, 
                                String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.articleCommentDtos = articleCommentDtos;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos, String title, String content, 
                                        String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleWithCommentsDto(id, userAccountDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);                 
    }

}
