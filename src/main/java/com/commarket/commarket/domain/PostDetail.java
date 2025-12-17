package com.commarket.commarket.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "post_details")
public class PostDetail {

    // post_details.post_id 는 PK이면서 posts.post_id 를 참조(FK)하는 구조
    @Id
    @Column(name = "post_id")
    private Long postId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(name = "Field2", nullable = false)
    private Integer tradeMethod;

    //희망장소 place TEXT_BODY NOT NULL
    @Lob
    @Column(name = "place", nullable = false)
    private String place;

    // description TEXT_BODY NULL
    @Lob
    @Column(name = "description")
    private String description;

    //  bookmark_num INT NOT NULL
    @Column(name = "bookmark_num", nullable = false)
    private Integer bookmarkNum;

    protected PostDetail() {
    }

    public PostDetail(Integer tradeMethod, String place, String description) {
        this.tradeMethod = tradeMethod;
        this.place = place;
        this.description = description;
        this.bookmarkNum = 0; // 기본값
    }

    // Post에서 setDetail() 할 때 내부적으로 사용
    void setPost(Post post) {
        this.post = post;
    }

    // ===== Getter (필요한 것만) =====
    public Long getPostId() {
        return postId;
    }

    public Integer getTradeMethod() {
        return tradeMethod;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public Integer getBookmarkNum() {
        return bookmarkNum;
    }

    // 찜 수 증감 같은 기능이 필요하면 아래처럼 메서드로 관리 가능
    public void increaseBookmark() {
        this.bookmarkNum += 1;
    }

    public void decreaseBookmark() {
        if (this.bookmarkNum > 0) this.bookmarkNum -= 1;
    }
}
