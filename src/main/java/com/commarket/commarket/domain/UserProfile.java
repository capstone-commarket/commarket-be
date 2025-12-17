package com.commarket.commarket.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_profiles")
public class UserProfile {
//PK가 profile_id(DB BIGINT 타입이므로 BIGINT 유지)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;
//1대1관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 50)
    private String nickname;

    @Column(name = "is_email_verified", nullable = false)
    private boolean emailVerified; //이메일 인증 완료 여부

    @Builder
    public UserProfile(User user, String nickname, boolean emailVerified){
        this.user = user;
        this.nickname = nickname;
        this.emailVerified = emailVerified;
    }

    public void updateNickname(String newNickname){
        this.nickname = newNickname;
    }
    public void verifyEmail(){
        this.emailVerified = true;
    }

}
