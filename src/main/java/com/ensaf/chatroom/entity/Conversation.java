package com.ensaf.chatroom.entity;

import com.ensaf.entity.NumericPersistable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Table(name = "conversations")
@Entity
@SuperBuilder
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Conversation extends NumericPersistable<Long> {

    @Column(length = 40)
    private String name; // Nom de la conversation (utilisé pour les groupes)
    private String image; // Photo ou image associée à la conversation

    @Column(nullable = false)
    private boolean groupChat = false; // Indique si la conversation est un groupe ou privé


}