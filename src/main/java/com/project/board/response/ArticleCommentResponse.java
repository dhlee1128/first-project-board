package com.project.board.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.project.board.dto.ArticleCommentDto;

public class ArticleCommentResponse implements Serializable{
    
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String email;
    private String nickname;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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


    public ArticleCommentResponse(Long id, String content, LocalDateTime createdAt, String email, String nickname) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
    }

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleCommentResponse(id, content, createdAt, email, nickname);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        String nickname = dto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.getUserAccountDto().getUserId();
        }

        return new ArticleCommentResponse(
                dto.getId(),
                dto.getContent(),
                dto.getCreatedAt(),
                dto.getUserAccountDto().getEmail(),
                nickname
        );
    }


}
