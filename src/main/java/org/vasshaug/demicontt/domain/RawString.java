package org.vasshaug.demicontt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RawString {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Lob
    private String rawString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRawString() {
        return rawString;
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawString)) return false;
        RawString rawString1 = (RawString) o;
        return Objects.equals(id, rawString1.id) && Objects.equals(rawString, rawString1.rawString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rawString);
    }

    @Override
    public String toString() {
        return "RawString{" +
                "id=" + id +
                ", rawString='" + rawString + '\'' +
                '}';
    }
}
