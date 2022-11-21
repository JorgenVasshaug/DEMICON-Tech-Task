package org.vasshaug.demicontt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.vasshaug.demicontt.domain.Result;
import org.vasshaug.demicontt.frontenddomain.Country;
import org.vasshaug.demicontt.service.ResultsService;
import org.vasshaug.demicontt.utility.FrontEndConverter;
import org.vasshaug.demicontt.utility.RandomuserAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return RandomuserAPI.getRaw(url, userSize);
    }

    // Fetch data from randomuser API, in case of error, fetch from DB
    @GetMapping("/")
    public String getRandomusers() {
        Result output;

        // Get result from API
        try {
            output = RandomuserAPI.getResults(url, userSize);
        } catch ( RestClientException e) {
            // In case of an unsuccessful synchronization attempt, return data from the last successful synchronization
            logger.info("Get from DB");
            Iterable results = resultsService.list();
            logger.info("Fetched : " + results);
            output = (Result) results.iterator().next();
            // @TODO VERIFY THAT THIS WORKS
        }

        // Convert response to frontend JSON format
        Map<String, Country> response = FrontEndConverter.convertToFrontEnd(output);
        logger.info("response = " + response);

        // We do not want the MAP key attribute in the response
        List<Country> list = new ArrayList<Country>(response.values());
        logger.info("list = " + list);

        String json = null;
        // Convert to JSON
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            logger.info("" + e);
        }
        // Add a countries wrapper on the result
        return "{\"countries\":" + json + "}";
    }
}
