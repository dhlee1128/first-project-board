package com.project.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.board.dto.ArticleCommentDto;
import com.project.board.repository.ArticleCommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return List.of();
    }

    public void saveArticleComment(ArticleCommentDto dto) {
    }

    public void updateArticleComment(ArticleCommentDto dto) {
    }

    public void deleteArticleComment(Long articleCommentId) {
    }

}
