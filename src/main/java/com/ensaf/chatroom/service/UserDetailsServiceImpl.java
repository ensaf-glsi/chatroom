package com.ensaf.chatroom.service;

import com.ensaf.chatroom.dao.UserRepository;
import com.ensaf.chatroom.dao.specification.UserSpecification;
import com.ensaf.chatroom.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("load user by username : {}", username);
        User user = userRepository.findOne(UserSpecification.byUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        log.debug("user founded : {}", user.getEmail());
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(),
                new ArrayList<>());
    }
}
