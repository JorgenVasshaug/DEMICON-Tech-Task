package org.vasshaug.demicontt.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vasshaug.demicontt.controller.DemiconTTRestController;
import org.vasshaug.demicontt.domain.frontend.Country;

import java.util.List;

public class JSONConverter {
    //Create a logger
    private static final Logger logger = LoggerFactory.getLogger(DemiconTTRestController.class);

    public static String convertFrontEndPOJOtoJSONString(List<Country> list) {
        // Convert response to JSON
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting to JSON String: " + e);
            return "";
        }
        // Add a countries wrapper on the result
        return "{\"countries\":" + json + "}";
    }

}
