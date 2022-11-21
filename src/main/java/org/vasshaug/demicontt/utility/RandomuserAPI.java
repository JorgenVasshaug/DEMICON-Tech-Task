package org.vasshaug.demicontt.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.vasshaug.demicontt.domain.Result;

// Utility class for fetching data from randomuser API
// Separated out into own class due to code reuse
public class RandomuserAPI {
    private static final Logger logger = LoggerFactory.getLogger(RandomuserAPI.class);

    // Fetches the raw data from randomuser API without processing
    public static String getRaw(String url, String userSize) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        return restTemplate.getForObject(newUrl, String.class);
    }

    // Fetches the data from randomuser and parses it into JSON using jackson package
    public static Result getResults(String url, String userSize) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        return restTemplate.getForObject(newUrl, Result.class);
    }
}
