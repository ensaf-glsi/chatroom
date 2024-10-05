package com.ensaf.chatroom.entity;

import com.ensaf.entity.NumericPersistable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@SuperBuilder
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Message extends NumericPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false)
    private LocalDateTime sentDate;

    @Column(columnDefinition = "TEXT")
    private String content;

}