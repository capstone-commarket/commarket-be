package com.commarket.commarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reports",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","post_id"})
)
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Report(Long userId, Long postId, String reason) {
        this.userId = userId;
        this.postId = postId;
        this.reason = reason;
    }
}