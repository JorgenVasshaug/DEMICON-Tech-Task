package org.vasshaug.demicontt.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.vasshaug.demicontt.domain.ResultElement;

public class RandomuserAPI {
    private static final Logger logger = LoggerFactory.getLogger(RandomuserAPI.class);

    public String getRaw(String url, String userSize) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        return restTemplate.getForObject(newUrl, String.class);
    }

    public ResultElement getResults(String url, String userSize) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url + "&results=" + userSize;
        logger.info("Url = " + newUrl);
        return restTemplate.getForObject(newUrl, ResultElement.class);
    }
}
