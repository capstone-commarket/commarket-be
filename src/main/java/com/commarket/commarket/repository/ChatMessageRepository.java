package com.example.demo.repository; // 패키지 이름이 demo로 수정되었는지 확인하세요.

import com.example.demo.model.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    
    // 특정 roomId의 모든 메시지를 시간순으로 조회하는 메서드
    List<ChatMessageEntity> findByRoomIdOrderByTimestampAsc(String roomId);
}