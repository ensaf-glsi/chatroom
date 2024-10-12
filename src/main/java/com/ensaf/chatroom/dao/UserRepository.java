package com.ensaf.chatroom.dao;

import com.ensaf.chatroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    // JPQL
    @Query("select (count(u) > 0) from User u where upper(u.username) = upper(?1)")
    boolean usernameExits(String username);
    @Query("select (count(u) > 0) from User u where upper(u.email) = upper(:email)")
    boolean emailExits(String email);

    // SQL NATIVE
    @Query(value = """
            select (count(*) > 0) from users u  +
            where upper(u.username) = upper(?1)
        """, nativeQuery = true)
    boolean usernameExitsSqlNative(String username);
    @Query(value = "select (count(*) > 0) from users u where upper(u.email) = upper(:email)", nativeQuery = true)
    boolean emailExitsSqlNative(String email);


}
