package com.commarket.commarket.repository;

import com.commarket.commarket.domain.Notice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, String> {

    Optional<Notice> findByExternalId(String externalId);
}