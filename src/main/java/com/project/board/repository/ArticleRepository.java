package com.project.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
    
}
