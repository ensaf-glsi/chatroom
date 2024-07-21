package com.ensaf.chatroom.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
//@AllArgsConstructor
////@NoArgsConstructor(force = true)
//@Getter @Setter
//@ToString
public class User {

    @EqualsAndHashCode.Include
    private String id;
    private String username;
//    private String phone;
//
    @ToString.Exclude
    private String password;
//    private String firstname;
//    private String lastname;

}
