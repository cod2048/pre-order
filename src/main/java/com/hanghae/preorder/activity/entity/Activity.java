package com.hanghae.preorder.activity.entity;

import com.hanghae.preorder.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ActivityType type;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    public Activity() { }

    public Activity(User user, ActivityType type, Long targetId) {
        this.user = user;
        this.type = type;
        this.targetId = targetId;
    }

    public enum ActivityType {
        COMMENT,
        LIKE,
        FOLLOW
    }
}
