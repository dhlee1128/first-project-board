package com.project.board.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.project.board.dto.ArticleWithCommentsDto;

public class ArticleWithCommentResponse  implements Serializable{
    private Long id;
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String email;
    private String nickname;
    private String userId;
    private Set<ArticleCommentResponse> articleCommentResponses;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<ArticleCommentResponse> getArticleCommentResponses() {
        return this.articleCommentResponses;
    }

    public void setArticleCommentResponses(Set<ArticleCommentResponse> articleCommentResponses) {
        this.articleCommentResponses = articleCommentResponses;
    }


    public ArticleWithCommentResponse(Long id, String title, String content, String hashtag, LocalDateTime createdAt, 
                                    String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.articleCommentResponses = articleCommentResponses;
    }

    public static ArticleWithCommentResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, 
                                        String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentResponses) 
    {
        return new ArticleWithCommentResponse(id, title, content, hashtag, createdAt, email, nickname, userId, articleCommentResponses);
    }

    public static ArticleWithCommentResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.getUserAccountDto().getUserId();
        }

        return new ArticleWithCommentResponse(
            dto.getId(),
            dto.getTitle(),
            dto.getContent(),
            dto.getHashtag(),
            dto.getCreatedAt(),
            dto.getUserAccountDto().getEmail(),
            nickname,
            dto.getUserAccountDto().getUserId(),
            dto.getArticleCommentDtos().stream()
                .map(ArticleCommentResponse::from)
                .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }


}
