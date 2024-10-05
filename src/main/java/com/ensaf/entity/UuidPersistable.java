package com.ensaf.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UuidPersistable extends AbstractPersistable<String> {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(length = 36)
    private String id;
}
