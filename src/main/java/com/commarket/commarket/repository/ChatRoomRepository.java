package com.commarket.commarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commarket.commarket.domain.ChatRoomEntity;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    // "이 물건(productId)을 사려는 이 사람(buyerId)의 방이 있나요?"
    Optional<ChatRoomEntity> findByProductIdAndBuyerId(String productId, String buyerId);
}