package com.commarket.commarket.dto.request;

import com.commarket.commarket.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostStatusRequest {
    private boolean active;  // 변경할 상태값

    
}