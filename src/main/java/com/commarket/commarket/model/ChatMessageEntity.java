package com.example.demo.model; // 패키지 이름이 demo로 수정되었는지 확인하세요.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 채팅방 ID (중고거래 상품 ID 등)
    private String roomId; 
    
    // 메시지 발신자
    private String sender; 
    
    // 메시지 내용
    @Column(columnDefinition = "TEXT")
    private String message;

    // 메시지 타입 (ENTER, TALK, LEAVE)을 문자열로 저장
    @Enumerated(EnumType.STRING) 
    private MessageType type; 
    
    // 메시지 전송 시간
    private Long timestamp; 
}