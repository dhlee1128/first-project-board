package com.project.board.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy"),
})
@Entity
public class ArticleComment extends AuditionFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Setter @ManyToOne(optional = false) private Article article; // 게시글 (ID)
    @Setter @Column(nullable = false, length = 500) private String content; // 본문


    protected ArticleComment() {}

    public ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }
    
    public ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ArticleComment)) {
            return false;
        }
        ArticleComment articleComment = (ArticleComment) o;
        return id == articleComment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
