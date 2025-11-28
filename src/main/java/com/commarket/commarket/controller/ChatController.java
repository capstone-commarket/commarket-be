package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatMessageEntity;
import com.example.demo.model.MessageType;
import com.example.demo.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import java.time.Instant;

@Controller
@RequiredArgsConstructor // 생성자 주입 자동화 (Lombok)
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {

        if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
        return; // 아무 일도 하지 않고 종료 (DB 저장 X, 전송 X)
        }
        
        // 1. 입장 메시지 가공 로직 (이 부분이 중요합니다!)
        if (MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

        // 2. 타임스탬프 생성
        message.setTimestamp(Instant.now().toEpochMilli());
        
        // 3. 데이터베이스 저장 (Entity로 변환)
        ChatMessageEntity entity = new ChatMessageEntity(
            null, // ID 자동 생성
            message.getRoomId(),
            message.getSender(),
            message.getMessage(),
            message.getType(),
            message.getTimestamp()
        );
        chatMessageRepository.save(entity);

        // 4. 실시간 전송
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}