package com.ensaf.chatroom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Entity
@SuperBuilder
@Getter @Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @Transient
    private Integer age;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}