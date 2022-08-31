package com.project.board.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.board.domain.type.SearchType;
import com.project.board.domain.Article;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleWithCommentsDto;
import com.project.board.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null || searchKeyword.isEmpty()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        Page<ArticleDto> result;
        switch (searchType) {
            case TITLE : 
                result = articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from); 
                break;
            case CONTENT : 
                result = articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from); 
                break;
            case ID : 
                result = articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from); 
                break;
            case NICKNAME : 
                result = articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from); 
                break;
            case HASHTAG : 
                result = articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from); 
                break;
        };

        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.getId());
            if (dto.getTitle() != null && !dto.getTitle().isEmpty()) { article.setTitle(dto.getTitle()); }
            if (dto.getContent() != null && !dto.getContent().isEmpty()) { article.setContent(dto.getContent()); }
            article.setHashtag(dto.getHashtag());
        } catch(EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }
    
}
