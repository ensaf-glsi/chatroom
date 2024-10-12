package com.ensaf.chatroom.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@RequiredArgsConstructor
public class UserCriteria {

    private String firstname;
    private String lastname;
}
