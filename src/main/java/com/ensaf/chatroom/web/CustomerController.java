package com.ensaf.chatroom.web;

import com.ensaf.chatroom.entity.Customer;
import com.ensaf.chatroom.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// GET /api/v1/customers : récuperer la liste des clients
// GET /api/v1/customers/1 : récuperer un client par son id
// POST /api/v1/customers {data} : creer un client
// PUT /api/v1/cutomers/1 {data} : modifier un client
// DELETE /api/v1/cutomers/1 : supprimer un client

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long customerId, @RequestBody Customer customer) {
        customerService.update(customerId, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @GetMapping("/{id}")
    // <=> @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

}
