package com.ensaf.chatroom.entity;

import com.ensaf.entity.UuidPersistable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Table(name = "users")
@Entity
@SuperBuilder
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class User extends UuidPersistable {

    @Size(max = 60)
    @NotBlank
    @Column(length = 60, nullable = false)
    private String username;

    @NotBlank
    @ToString.Exclude
    @Size(min = 6, max = 20)
    @Column(length = 20)
    private String password;

    @Size(max = 60)
    @Email
    @NotBlank
    @Column(length = 60, nullable = false)
    private String email;

    @Pattern(regexp = "^[+0]?[1-9]\\d{1,14}$")
    @Column(length = 16)
    private String phone;

    @Size(max = 40)
    @Column(length = 40)
    private String firstname;

    @Size(max = 40)
    @Column(length = 40)
    private String lastname;

    @Past
    private LocalDate birthDate;

    @Size(max = 255)
    private String image;

}
