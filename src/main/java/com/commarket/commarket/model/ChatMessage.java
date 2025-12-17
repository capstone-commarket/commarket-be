package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    
    private MessageType type; // 메시지 타입 (ENTER, TALK, LEAVE)
    private String roomId;    // 채팅방 ID (중고거래 상품 ID나 고유 식별자)
    private String sender;    // 발신자 이름 또는 ID
    private String message;   // 메시지 내용
    private long timestamp;   // 메시지 전송 시간 (선택)
}