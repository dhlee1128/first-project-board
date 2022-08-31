package com.project.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.board.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    
}
