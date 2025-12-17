package com.commarket.commarket.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long Userid; //유저ID

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password_hash", nullable = false,length = 255)
    private String password_hash;

    @Column(name = "email", nullable = false,unique = true, length = 100)
    private String email; //학교 이메일

    @Column(name = "name", length = 50)
    private String name;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @Column( name = "phoneNUmber",length = 15, nullable = false)
    private String  phoneNumber;



    @Builder
    public User(String username, String password_hash, String email, String name, String  phoneNumber){
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.phoneNumber = phoneNumber;
        this.name = name;

    }
    public void updatePassword(String newPassword){
        this.password_hash = newPassword;
    }
}
