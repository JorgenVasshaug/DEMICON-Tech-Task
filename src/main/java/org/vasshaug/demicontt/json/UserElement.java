package org.vasshaug.demicontt.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.vasshaug.demicontt.entity.Result;

import javax.persistence.*;
import java.util.Objects;

/*
Contains the User element from randomuser
{
      "gender": "male",
      "name": {
            <Name Class>
      },
      "email": "tristan.bergan@example.com",
      "nat": "NO"
    }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class UserElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private NameElement name;
    private String gender;

    // In the call on the randomuser API we specify that we only want the nationality element from the location structure
    @JsonProperty("nat")
    private String location;
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private ResultElement result;

    public UserElement() {
    }

    public NameElement getName() {
        return name;
    }

    public void setName(NameElement name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResultElement getResult() {
        return result;
    }

    public void setResult(ResultElement result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserElement)) return false;
        UserElement that = (UserElement) o;
        return Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(location, that.location) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, location, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
