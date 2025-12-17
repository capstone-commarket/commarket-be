package com.commarket.commarket.repository; // 패키지 이름이 demo로 수정되었는지 확인하세요.

import org.springframework.data.jpa.repository.JpaRepository;

import com.commarket.commarket.domain.ChatMessageEntity;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    
    // 특정 roomId의 모든 메시지를 시간순으로 조회하는 메서드
    List<ChatMessageEntity> findByRoomIdOrderByTimestampAsc(String roomId);
}