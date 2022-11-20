package org.vasshaug.demicontt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Table(name="randomuser")  //user is a reserved keyword in H2, so using randomuser as table name
public class User {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Name name;
    private String gender;

    // In the call on the randomuser API we specify that we only want the nationality element from the location structure
    @JsonProperty("nat")
    private String location;
    private String email;
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Result result;

    public User() {
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        logger.info("UserElement : Setting result : " + result);
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(location, that.location) && Objects.equals(email, that.email) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, location, email, result);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", result=" + result +
                '}';
    }
}
