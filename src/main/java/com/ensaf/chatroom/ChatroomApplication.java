package com.ensaf.chatroom;

import com.ensaf.chatroom.dao.CustomerRepository;
import com.ensaf.chatroom.entity.Customer;
import com.ensaf.chatroom.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ChatroomApplication {

//	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
	}

//	@Bean
	public CommandLineRunner demo(CustomerService customerService, CustomerRepository repository) {
		return args -> {
			log.trace("A TRACE Message");
			log.debug("A DEBUG Message");
			log.info("An INFO Message");
			log.warn("A WARN Message");
			log.error("An ERROR Message");

			// create a few customers
			customerService.create(new Customer("Jack", "Bauer"));
			customerService.create(new Customer("Chloe", "O'Brian"));
			customerService.create(new Customer("Kim", "bauer"));
			customerService.create(new Customer("David", "Palmer"));
			customerService.create(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
//			customerService.findAll().forEach(c -> log.info(c.toString()));
			log.info("");

			// fetch an individual customer by ID
//			customerService.findById(1L).ifPresent(c -> {
//				log.info("Customer found with findById(1L):");
//				log.info("--------------------------------");
//				log.info(c.toString());
//				log.info("");
//			});

//			customerService.findById(10L).ifPresentOrElse(
//				c -> {
//					log.info("Customer found with findById(10L):");
//					log.info("--------------------------------");
//					log.info(c.toString());
//					log.info("");
//				},
//				() -> log.info("No customer present with id 10L")
//			);

//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastNameContains("aue").forEach(c -> log.info(c.toString()));
//			log.info("");
//			// fetch customers by first name and last name
//			log.info("Customer found with findByFirstNameIgnoreCaseAndLastName(\"david\", \"Palmer\"):");
//			log.info("--------------------------------------------");
//			repository.findByFirstNameIgnoreCaseAndLastName("david", "Palmer").forEach(c -> log.info(c.toString()));
//			log.info("");
		};
	}
}
