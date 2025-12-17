package com.commarket.commarket.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String externalId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String postedTime;

    @Builder
    public Notice(String externalId, String title, String content, String url, String postedTime) {
        this.externalId = externalId;
        this.title = title;
        this.content = content;
        this.url = url;
        this.postedTime = postedTime;
    }

    public static Notice createExternal(
            String externalId,
            String title,
            String content,
            String url,
            String postedTime
    ) {
        return Notice.builder()
                .externalId(externalId)
                .title(title)
                .content(content)
                .url(url)
                .postedTime(postedTime)
                .build();
    }

    // ✅ 반드시 추가
    public void updateFromExternal(
            String title,
            String content,
            String url,
            String postedTime
    ) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.postedTime = postedTime;
    }
}