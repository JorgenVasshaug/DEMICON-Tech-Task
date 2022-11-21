package org.vasshaug.demicontt.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.vasshaug.demicontt.domain.Result;

public class RandomuserAPI {
    private static final Logger logger = LoggerFactory.getLogger(RandomuserAPI.class);

    public static String getRaw(String url, String userSize) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        return restTemplate.getForObject(newUrl, String.class);
    }

    public static Result getResults(String url, String userSize) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        return restTemplate.getForObject(newUrl, Result.class);
    }
}
