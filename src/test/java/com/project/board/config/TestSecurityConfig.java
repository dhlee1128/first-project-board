package com.project.board.config;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.project.board.domain.UserAccount;
import com.project.board.repository.UserAccountRepository;

@Import(SecurityConfig.class)
public class TestSecurityConfig {
    
    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetup() {

        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "dhtest",
                "pw",
                "dh-test@email.com",
                "dh-test",
                "test-memo"
        )));
        
    }

}
