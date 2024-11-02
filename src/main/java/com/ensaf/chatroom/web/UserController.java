package com.ensaf.chatroom.web;

import com.ensaf.chatroom.dto.UserCriteria;
import com.ensaf.chatroom.entity.User;
import com.ensaf.chatroom.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// GET /api/v1/users : récuperer la liste des clients
// GET /api/v1/users/1 : récuperer un client par son id
// POST /api/v1/users {data} : creer un client
// PUT /api/v1/users/1 {data} : modifier un client
// DELETE /api/v1/users/1 : supprimer un client

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String userId, @Valid @RequestBody User user) {
        userService.update(userId, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    // <=> @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public User findById(@PathVariable String id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> findAll(UserCriteria criteria) {
        return userService.findAll(criteria);
    }

    @GetMapping("/{id}/revisions")
    public Object findRevisions(@PathVariable String id, Pageable pageable) {
        return userService.findRevisions(id, pageable);
    }

    @GetMapping("/{id}/revisions/{revision}")
    public Object getRevision(@PathVariable String id, @PathVariable Long revision) {
        return userService.findRevision(id, revision);
    }
}
