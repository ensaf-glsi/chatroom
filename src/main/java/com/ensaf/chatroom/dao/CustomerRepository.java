package com.ensaf.chatroom.dao;

import com.ensaf.chatroom.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastNameContains(String lastName);

    List<Customer> findByFirstNameIgnoreCaseAndLastName(String firstName, @Nullable String lastName);
//    Optional<Customer> findById(Long id);
//    List<Customer> findAll();
//
//    Customer save(Customer customer);
}
