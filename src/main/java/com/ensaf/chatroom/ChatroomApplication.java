package com.ensaf.chatroom;

import com.ensaf.chatroom.dao.CustomerRepository;
import com.ensaf.chatroom.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ChatroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return args -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(c -> log.info(c.toString()));
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L).ifPresent(c -> {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(c.toString());
				log.info("");
			});

			repository.findById(10L).ifPresentOrElse(
				c -> {
					log.info("Customer found with findById(10L):");
					log.info("--------------------------------");
					log.info(c.toString());
					log.info("");
				},
				() -> log.info("No customer present with id 10L")
			);

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastNameContains("aue").forEach(c -> log.info(c.toString()));
			log.info("");
			// fetch customers by first name and last name
			log.info("Customer found with findByFirstNameIgnoreCaseAndLastName(\"david\", \"Palmer\"):");
			log.info("--------------------------------------------");
			repository.findByFirstNameIgnoreCaseAndLastName("david", "Palmer").forEach(c -> log.info(c.toString()));
			log.info("");
		};
	}
}
