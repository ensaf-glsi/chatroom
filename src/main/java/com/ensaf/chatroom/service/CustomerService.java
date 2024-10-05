package com.ensaf.chatroom.service;

import com.ensaf.chatroom.dao.CustomerRepository;
import com.ensaf.chatroom.entity.Customer;
import com.ensaf.chatroom.exception.BadRequestException;
import com.ensaf.chatroom.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

//    @Autowired
    private final CustomerRepository customerRepository;

//    @Autowired
//    public void setCustomerRepository(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @PostConstruct
    void init() {
        log.info("initialisation du bean customerService");
    }

    public Customer create(Customer customer) {
        String firstName = customer.getFirstName();
        if (!StringUtils.hasLength(firstName) || firstName.length() < 3) {
            throw new BadRequestException("Le prénom doit contenir au mois 3 caracteres.");
        }
        return customerRepository.save(customer);
    }

    public Customer getById(Long id) {
        return findById(id).orElseThrow(NotFoundException::new);
    }

    public void update(Long id, Customer customer) {
        // chercher le client dans la bd,
        // si le client n'existe pas on leve une exception
        Customer target = getById(id);
        BeanUtils.copyProperties(customer, target, "id");
        customerRepository.save(target);
    }

    public void delete(Long id) {
//        customerRepository.deleteById(id);
        customerRepository.delete(getById(id));
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        log.trace("find all customers");
        return customerRepository.findAll();
    }

}
