package com.project.board.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.board.domain.Article;
import com.project.board.domain.UserAccount;
import com.project.board.domain.constant.SearchType;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleWithCommentsDto;
import com.project.board.repository.ArticleRepository;
import com.project.board.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    
    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

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
            default: 
                result = null;
                break;
        };

        return result;
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("???????????? ???????????? - articleId: " + articleId));
    }

    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("???????????? ???????????? - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
        articleRepository.save(dto.toEntity(userAccount));
    }

    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());

            if (article.getUserAccount()==userAccount) {
                if (dto.getTitle() != null && !dto.getTitle().isEmpty()) { article.setTitle(dto.getTitle()); }
                if (dto.getContent() != null && !dto.getContent().isEmpty()) { article.setContent(dto.getContent()); }
                article.setHashtag(dto.getHashtag());
            }
        } catch(EntityNotFoundException e) {
            log.warn("????????? ???????????? ??????. ???????????? ??????????????? ????????? ????????? ?????? ??? ????????????. - ", e.getLocalizedMessage());
        }
    }

    public void deleteArticle(long articleId, String userId) {
        articleRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticlesViaHashtag(String hashtag, Pageable pageable) {
        if(hashtag == null || hashtag.isEmpty()) {
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtag(hashtag, pageable).map(ArticleDto::from);
    }

    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
    }
    
}
