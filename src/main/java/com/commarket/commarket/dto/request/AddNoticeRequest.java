package com.commarket.commarket.dto.request;

import com.commarket.commarket.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddNoticeRequest {
    private String externalId;
    private String title;
    private String content;
    private String url;
    private String postedTime;

    public Notice toEntity() {
        return Notice.builder()
                .externalId(externalId)
                .title(title)
                .content(content)
                .url(url)
                .postedTime(postedTime)
                .build();
    }
}