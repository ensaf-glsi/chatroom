package com.ensaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import static com.ensaf.chatroom.utils.ColumnDefinition.UUID_LENGTH;

@MappedSuperclass
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UuidAuditable extends AbstractAuditable<String> {

    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(length = UUID_LENGTH)
    @Id
    private String id;

}
