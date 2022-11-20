package org.vasshaug.demicontt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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
@Entity
public class ResultElement {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    @OrderColumn
    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)
    private UserElement[] results;

    public ResultElement() {
    }

    public UserElement[] getResults() {
        return results;
    }

    public void setResults(UserElement[] results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultElement)) return false;
        ResultElement that = (ResultElement) o;
        return Arrays.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(results);
    }

    @Override
    public String toString() {
        return "Results{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}