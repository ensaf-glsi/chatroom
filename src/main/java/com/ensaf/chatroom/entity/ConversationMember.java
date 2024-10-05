package com.ensaf.chatroom.entity;

import com.ensaf.entity.NumericPersistable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Table(name = "conversation_memebers")
@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ConversationMember extends NumericPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private boolean muted;


}
