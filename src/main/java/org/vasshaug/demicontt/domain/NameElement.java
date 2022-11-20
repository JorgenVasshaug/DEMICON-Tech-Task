package org.vasshaug.demicontt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Embeddable;
import java.util.Objects;

/* Contains the name element from randomuser
      "name": {
        "title": "Mr",
        "first": "Tristan",
        "last": "Bergan"
      },
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class NameElement {

    private String title;
    private String first;
    private String last;

    public NameElement() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NameElement)) return false;
        NameElement that = (NameElement) o;
        return Objects.equals(title, that.title) && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, first, last);
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