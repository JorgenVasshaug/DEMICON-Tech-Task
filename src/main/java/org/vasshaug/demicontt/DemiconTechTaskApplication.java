package org.vasshaug.demicontt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemiconTechTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemiconTechTaskApplication.class, args);
	}

	/* @TODO
       Refactoring
        - Make RandomuserAPI static with DI values, or move DI into the RandomUserAPI class and remove need for function variables
        - If possible, Combine entity & json classes into one that handles both JPA & JSON
        - Use @Data to simplify datamodel - https://projectlombok.org/features/Data
        - Use @AllArgsConstructor to simplify datamodel - https://projectlombok.org/features/constructor
		- jobtask:  previous records before saving into DB
		- Is it possible to replace RestController with just the Spring Data Rest dependency ?
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-data-rest</artifactId>
			</dependency>

     */

}
