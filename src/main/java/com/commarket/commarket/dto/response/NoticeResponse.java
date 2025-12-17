package com.commarket.commarket.dto.response;

import com.commarket.commarket.domain.Notice;
import lombok.Getter;

@Getter
public class NoticeResponse {
    private String externalId;
    private Long id;
    private String title;
    private String content;
    private String url;
    private String postedTime;

    public NoticeResponse(Notice notice) {
        this.externalId = notice.getExternalId();
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.url = notice.getUrl();
        this.postedTime = notice.getPostedTime();
    }
}