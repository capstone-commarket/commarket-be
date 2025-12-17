package com.commarket.commarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commarket.commarket.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByNickname(String nickname);
}
