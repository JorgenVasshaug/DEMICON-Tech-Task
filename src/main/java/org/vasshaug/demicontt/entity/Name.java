package org.vasshaug.demicontt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Contains the name element from randomuser
      "name": {
        "title": "Mr",
        "first": "Tristan",
        "last": "Bergan"
      },
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {

    private String title;
    private String first;
    private String last;

    public Name() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Name{" +
                "title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
