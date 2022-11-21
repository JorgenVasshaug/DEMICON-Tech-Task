package org.vasshaug.demicontt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class DemiconTechTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemiconTechTaskApplication.class, args);
	}

	/* @TODO
	   - Generate instructions for running
	   - Map domain to frontend format (plain POJO transformation) --> Check if there are better options
	   - Change Java to v8


       Refactoring
        - Make RandomuserAPI static with DI values, or move DI into the RandomUserAPI class and remove need for function variables
        - Try removing Results from persistence (remember to update that List<User> are returned instead of Result
		- jobtask:  delete previous records before saving into DB


		Consider
		- Is it possible to replace RestController with just the Spring Data Rest dependency ?
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-data-rest</artifactId>
			</dependency>
        - Use @Data to simplify datamodel - https://projectlombok.org/features/Data
        - Use @AllArgsConstructor to simplify datamodel - https://projectlombok.org/features/constructor
     */

}
