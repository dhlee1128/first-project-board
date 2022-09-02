package com.project.board.dto.request;

import com.project.board.dto.ArticleDto;
import com.project.board.dto.UserAccountDto;

public class ArticleRequest {

    private String title;
    private String content;
    private String hashtag;


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


    public ArticleRequest(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleRequest of(String title, String content, String hashtag) {
        return new ArticleRequest(title, content, hashtag);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }
    
}
