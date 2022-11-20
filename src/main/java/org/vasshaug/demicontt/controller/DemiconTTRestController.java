package org.vasshaug.demicontt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.vasshaug.demicontt.domain.ResultElement;
import org.vasshaug.demicontt.utility.RandomuserAPI;

@RestController
public class DemiconTTRestController {

    //Create a logger
    private static final Logger logger = LoggerFactory.getLogger(DemiconTTRestController.class);

    //inject configuration from application.properties
    @Value("${randomuser.endpoint.url}")
    private String url;

    @Value("${randomuser.endpoint.userSize}")
    private String userSize;

    @Value("${jobtask.period.in.milliseconds}")
    private String period;

    // For testing correct configuration of Service
    @GetMapping("/test")
    public String testMe() {
        return "Hello!<br><br>Url = " + url + "<br>UserSize = " + userSize + "<br>Period = " + period;
    }

    // Show raw result fetched from randomuser API
    @GetMapping("/raw")
    public String getRaw() {
        return new RandomuserAPI().getRaw(url, userSize);
    }

    /* Could we replace all this with just the Spring Data Rest dependency ?
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
     */

    // Fetch results from randomuser and convert to POJOs using the Jackson library
    @GetMapping("/")
    public ResultElement getResults() {
        // Result output = new RandomuserAPI().getResults(url, userSize);
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        ResultElement output = restTemplate.getForObject(newUrl, ResultElement.class);

        /* @TODO convert to expected output format
        { "countries": [
            {
              "name": "<country>",
              "users": [{
                     "name": "<userName>",
                     "gender": "<gender>",
                     "email": "<email>"
              }]
            }
         ]}
         */

        // @TODO In case of an unsuccessful synchronization attempt, return data from the last successful synchronization

        return output;
    }


}
