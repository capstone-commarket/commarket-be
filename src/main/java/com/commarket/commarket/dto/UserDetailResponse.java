package com.commarket.commarket.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailResponse {
    private Long userid;
    private String username;
    private String email;
    private String name;
    private String nickname;
    private boolean emailVerified;
}
