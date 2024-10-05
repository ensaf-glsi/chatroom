package com.ensaf.chatroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ChatroomApplication {

//	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
	}

}
