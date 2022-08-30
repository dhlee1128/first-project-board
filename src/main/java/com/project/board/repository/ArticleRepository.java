package com.project.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.project.board.domain.Article;
import com.project.board.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;

@RepositoryRestResource
public interface ArticleRepository extends 
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>
{
    // 선택적인 field만 검색 가능하도록 하기
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true); // 선택적으로
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        // bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like 's{v}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%s{v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%s{v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%s{v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like '%s{v}%'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%s{v}%'
    }
    
}
