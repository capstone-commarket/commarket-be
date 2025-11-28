package com.example.demo.controller;

import com.example.demo.model.ChatMessageEntity;
import com.example.demo.model.ChatRoomEntity;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.repository.ChatRoomRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor // 생성자 주입 자동화 (Lombok)
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    /**
     * [채팅방 생성 또는 조회]
     * 구매자가 "채팅하기"를 눌렀을 때 호출
     * POST /api/chat/room
     */
    @PostMapping("/room")
    public String createOrGetChatRoom(@RequestBody ChatRequest chatRequest) {
        String productId = chatRequest.getProductId();
        String buyerId = chatRequest.getBuyerId();
        String sellerId = chatRequest.getSellerId();

        // 1. 이미 존재하는 방인지 확인 (상품ID + 구매자ID 조합)
        return chatRoomRepository.findByProductIdAndBuyerId(productId, buyerId)
                .map(ChatRoomEntity::getRoomId) // 방이 있으면 기존 방 번호 리턴
                .orElseGet(() -> {
                    // 2. 없으면 새로운 방 번호(UUID) 생성 후 DB 저장
                    String newRoomId = UUID.randomUUID().toString();
                    
                    ChatRoomEntity newRoom = ChatRoomEntity.builder()
                            .roomId(newRoomId)
                            .productId(productId)
                            .sellerId(sellerId)
                            .buyerId(buyerId)
                            .build();
                    
                    chatRoomRepository.save(newRoom);
                    return newRoomId; // 새로 만든 방 번호 리턴
                });
    }

    /**
     * [이전 채팅 내역 조회]
     * 채팅방에 입장했을 때 과거 대화 내용을 불러옴
     * GET /api/chat/room/{roomId}/messages
     */
    @GetMapping("/room/{roomId}/messages")
    public List<ChatMessageEntity> loadMessages(@PathVariable String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }

    // 요청 데이터를 받을 DTO (내부 클래스)
    @Getter @Setter
    public static class ChatRequest {
        private String productId;
        private String sellerId;
        private String buyerId;
    }
}