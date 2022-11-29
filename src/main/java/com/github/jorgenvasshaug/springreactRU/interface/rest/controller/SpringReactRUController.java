package com.github.jorgenvasshaug.springreactRU.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jorgenvasshaug.springreactRU.domain.entity.frontend.Country;
import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.Result;
import com.github.jorgenvasshaug.springreactRU.domain.service.ResultsService;
import com.github.jorgenvasshaug.springreactRU.utility.FrontEndConverter;
import com.github.jorgenvasshaug.springreactRU.utility.JSONConverter;
import com.github.jorgenvasshaug.springreactRU.utility.RandomuserAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import com.github.jorgenvasshaug.springreactRU.domain.entity.string.RawString;
import com.github.jorgenvasshaug.springreactRU.domain.service.RawStringService;

import java.util.List;

@CrossOrigin()  // To enable FrontEnd to access the BackEnd (adds HTTP Header 'Access-Control-Allow-Origin')
@RestController
public class SpringReactRUController {

    //Create a logger
    private static final Logger logger = LoggerFactory.getLogger(SpringReactRUController.class);

    //inject configuration from application.properties
    @Value("${randomuser.endpoint.url}")
    private String url;

    @Value("${randomuser.endpoint.userSize}")
    private String userSize;

    @Value("${jobtask.period.in.milliseconds}")
    private String period;
    private final ResultsService resultsService;
    private final RawStringService rawStringService;

    public SpringReactRUController(ResultsService resultsService, RawStringService rawStringService) {
        this.resultsService = resultsService;
        this.rawStringService = rawStringService;
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

    // Fetch rawString from DB and parse to expected format
    @GetMapping("/rawString")
    public String getRawString() {
        // Fetch the String representation of the Randomuser JSON from DB
        Iterable<RawString> rawStringIterable = rawStringService.list();


        if (rawStringIterable.iterator().hasNext()) {
            // Found data, parse and then convert to frontend format
            RawString rawString;
            rawString = rawStringIterable.iterator().next();

            // Parse from JSON String to JSON POJOS
            ObjectMapper mapper = new ObjectMapper();
            Result result;
            try {
                result = mapper.readValue(rawString.getRawString(), Result.class);
            } catch (JsonProcessingException e) {
                logger.info("" + e);
                return "";
            }

            // Convert response to frontend domain structure
            List<Country> list = FrontEndConverter.convertToFrontEnd(result);

            // Convert to JSON String
            return JSONConverter.convertFrontEndPOJOtoJSONString(list);
        } else {
            // return empty if nothing in DB
            return "";
        }
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
            Iterable<Result> results = resultsService.list();
            logger.info("Fetched : " + results);
            output = results.iterator().next();
        }

        // Convert response to frontend domain structure
        List<Country> list = FrontEndConverter.convertToFrontEnd(output);
        logger.info("list = " + list);

        // Convert to JSON String
        return JSONConverter.convertFrontEndPOJOtoJSONString(list);
    }
}
