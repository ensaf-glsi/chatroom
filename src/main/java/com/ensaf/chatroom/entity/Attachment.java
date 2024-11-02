package com.ensaf.chatroom.entity;

import com.ensaf.entity.UuidPersistable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter @ToString
@SuperBuilder
@RequiredArgsConstructor
public class Attachment extends UuidPersistable {
    @Column(length = 100)
    private String filename;
    @Column(length = 200)
    private String contentType;
    private Long size;

}