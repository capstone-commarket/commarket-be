package com.commarket.commarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commarket.commarket.domain.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    // findById는 JpaRepository에 이미 정의되어 있으므로 생략 가능하지만, 유지해도 무방합니다.
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}