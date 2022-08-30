package com.project.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.project.board.domain.ArticleComment;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>{
    
}
