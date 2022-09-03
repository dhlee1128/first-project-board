package com.project.board.controller;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.board.config.SecurityConfig;
import com.project.board.config.TestSecurityConfig;
import com.project.board.service.ArticleService;
import com.project.board.service.PaginationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@DisplayName("View 컨트롤러 - 인증")
@Import(TestSecurityConfig.class)
@WebMvcTest
public class AuthControllerTest {

    private final MockMvc mvc;

    @MockBean private ArticleService articleService;
    @MockBean private PaginationService paginationService;

    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    // @DisplayName("[view][GET] 로그인 페이지 - 정상 호출")
    // @Test
    // void givenNothing_whenTryingToLogIn_thenReturnsLogInView() throws Exception {
    //     // Given

    //     // When & Then
    //     mvc.perform(get("/login"))
    //             .andExpect(status().isOk())
    //             .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    //     then(articleService).shouldHaveNoInteractions();
    //     then(paginationService).shouldHaveNoInteractions();
    // }
    
}
