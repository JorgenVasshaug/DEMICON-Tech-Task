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
	   - Update instructions on how to generate jar file


       Refactoring
        - Make RandomuserAPI static with DI values, or move DI into the RandomUserAPI class and remove need for function variables
        - If possible remove Results from persistence (remember to update that List<User> are returned instead of Result)
		- jobtask:  delete previous records before saving into DB
		- Better handling of possible exceptions
		- Move js code in app.js to own classes (components)
		- Add CSS to page


		Considerations
		- Is it possible to replace RestController with just the Spring Data Rest dependency ?
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-data-rest</artifactId>
			</dependency>
        - Use @Data to simplify datamodel - https://projectlombok.org/features/Data
        - Use @AllArgsConstructor to simplify datamodel - https://projectlombok.org/features/constructor
     */

}
