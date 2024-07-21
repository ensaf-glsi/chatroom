package com.ensaf.chatroom;

import com.ensaf.chatroom.entity.User;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        User u = new User();
        u.setId("u1");
        u.setUsername("username");
        u.setPassword("pass");
        System.out.println(u);

        User u2 = new User();
        u2.setId("u2");
        u2.setUsername("username 2");
        u2.setPassword("pass 2");
        System.out.println(u2);

        System.out.println("u =? u2 " + Objects.equals(u, u2));

        User.UserBuilder<?, ?> builder = User.builder();
        User u3 = User.builder().id("u3").username("username3").password("pass3").build();
    }
}
