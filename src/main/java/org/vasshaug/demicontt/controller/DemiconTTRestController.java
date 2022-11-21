package org.vasshaug.demicontt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.vasshaug.demicontt.domain.Result;
import org.vasshaug.demicontt.service.ResultsService;
import org.vasshaug.demicontt.utility.RandomuserAPI;

@CrossOrigin()  // To enable FrontEnd to access the BackEnd (adds HTTP Header 'Access-Control-Allow-Origin')
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
    private ResultsService resultsService;

    public DemiconTTRestController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

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

    // Fetch results from randomuser and convert to POJOs using the Jackson library

    @GetMapping("/main")
    public Result getResults() {
        Result output;
        try {
            // Fetch data from API
            output = new RandomuserAPI().getResults(url, userSize);
        } catch ( RestClientException e) {
            // @TODO VERIFY THAT THIS WORKS
            // In case of an unsuccessful synchronization attempt, return data from the last successful synchronization
            logger.info("Get from DB");
            Iterable results = resultsService.list();
            logger.info("Fetched : " + results);
            output = (Result) results.iterator().next();
        }

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

        return output;
    }

    @GetMapping("/")
    public String getWorkaround() {
        String response =
                "{\"countries\": [" +
                " {\"name\": \"DE\", \"users\": [" +
                "   {\"name\": \"Hans Fried\", \"gender\": \"Male\", \"email\": \"aa@bb.com\"}," +
                "   {\"name\": \"Eva Braun\", \"gender\": \"Female\", \"email\": \"eva@braun.com\"}" +
                " ]}," +
                " {\"name\": \"GB\", \"users\": [" +
                "   {\"name\": \"Tom Hanks\", \"gender\": \"Male\", \"email\": \"tom.hanks@movies.com\"}" +
                " ]}" +
                "]}";
        logger.info("Response : " + response);
        return response;
    }

    @GetMapping("/db")
    public Result getDB() {
        logger.info("Get from DB");
        Iterable results = resultsService.list();
        logger.info("Fetched : " + results);
        return (Result) results.iterator().next();
    }
}
