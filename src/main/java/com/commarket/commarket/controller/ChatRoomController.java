package com.commarket.commarket.controller;

import com.commarket.commarket.domain.ChatMessageEntity;
import com.commarket.commarket.domain.ChatRoomEntity;
import com.commarket.commarket.repository.ChatMessageRepository;
import com.commarket.commarket.repository.ChatRoomRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @PostMapping("/room")
    public String createOrGetChatRoom(@RequestBody ChatRequest chatRequest) {
        String productId = chatRequest.getProductId();
        String buyerId = chatRequest.getBuyerId();
        String sellerId = chatRequest.getSellerId();

        return chatRoomRepository.findByProductIdAndBuyerId(productId, buyerId)
                .map(ChatRoomEntity::getRoomId)
                .orElseGet(() -> {
                    String newRoomId = UUID.randomUUID().toString();

                    ChatRoomEntity newRoom = ChatRoomEntity.builder()
                            .roomId(newRoomId)
                            .productId(productId)
                            .sellerId(sellerId)
                            .buyerId(buyerId)
                            .build();

                    chatRoomRepository.save(newRoom);
                    return newRoomId;
                });
    }

    @GetMapping("/room/{roomId}/messages")
    // ("roomId")추가
    public List<ChatMessageEntity> loadMessages(@PathVariable("roomId") String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }

    @Getter @Setter
    public static class ChatRequest {
        private String productId;
        private String sellerId;
        private String buyerId;
    }
}