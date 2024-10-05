package com.ensaf.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class NumericPersistable<I extends Number> extends AbstractPersistable<I> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private I id;
}
