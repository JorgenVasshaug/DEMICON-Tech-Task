package org.vasshaug.demicontt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/*
Contains the results element from randomuser
{
  "results": [
    {
        <User class>
    }
    ],
  "info": {
    "seed": "eb7bffcf8d513e9a",
    "results": 10,
    "page": 1,
    "version": "1.4"
  }
}

"info" element is ignored
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    private User[] results;

    public Results() {
    }

    public User[] getResults() {
        return results;
    }

    public void setResults(User[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Results{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
