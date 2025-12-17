package com.commarket.commarket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_room")
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomId;    // 랜덤 생성된 고유 방 번호 (UUID)

    private String productId; // 상품 ID
    private String sellerId;  // 판매자 ID
    private String buyerId;   // 구매자 ID

    private LocalDateTime createdAt; // 방 생성 시간

    // 엔티티가 저장되기 전에 실행되어 시간 자동 저장
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}