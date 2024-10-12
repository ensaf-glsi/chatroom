package com.ensaf.chatroom.service;

import com.ensaf.chatroom.dao.UserRepository;
import com.ensaf.chatroom.dao.specification.UserSpecification;
import com.ensaf.chatroom.dto.UserCriteria;
import com.ensaf.chatroom.entity.User;
import com.ensaf.chatroom.exception.BadRequestException;
import com.ensaf.chatroom.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    void init() {
        log.info("initialisation du bean userService");
    }

    void checkUsername(User user) {
        if (userRepository.existsByUsernameIgnoreCase(user.getUsername())) {
            throw new BadRequestException("Un utilisateur avec le meme username existe deja dans la bd.");
        }
    }

    void checkEmail(User user) {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new BadRequestException("Un utilisateur avec le meme email existe deja dans la bd.");
        }
    }

    public User create(User user) {
        // controler unicité username
        checkUsername(user);
        // controler unicité email
        checkEmail(user);
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return findById(id).orElseThrow(NotFoundException::new);
    }

    public void update(Long id, User user) {
        // controler unicité username
        // controler unicité email
        // chercher le client dans la bd,
        // si le client n'existe pas on leve une exception
        User target = getById(id);
        BeanUtils.copyProperties(user, target, "id");
        userRepository.save(target);
    }

    public void delete(Long id) {
        userRepository.delete(getById(id));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll(UserCriteria criteria) {
        log.trace("find all users");
        return userRepository.findAll(
                Specification.allOf(
                        UserSpecification.byFirstnameContaining(criteria.getFirstname()),
                        UserSpecification.byLastnameContaining(criteria.getLastname())
                )
//                UserSpecification.byFirstnameContaining(criteria.getFirstname())
//                        .and(UserSpecification.byLastnameContaining(criteria.getLastname()))
        );
    }

}
