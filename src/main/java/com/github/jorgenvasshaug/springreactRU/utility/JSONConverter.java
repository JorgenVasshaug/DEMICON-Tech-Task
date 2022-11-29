package com.github.jorgenvasshaug.springreactRU.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.jorgenvasshaug.springreactRU.controller.SpringReactRUController;
import com.github.jorgenvasshaug.springreactRU.domain.entity.frontend.Country;

import java.util.List;

public class JSONConverter {
    //Create a logger
    private static final Logger logger = LoggerFactory.getLogger(SpringReactRUController.class);

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
