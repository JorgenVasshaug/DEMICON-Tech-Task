package org.vasshaug.demicontt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class User {
    private Name name;
    private String gender;

    // In the call on the randomuser API we specify that we only want the nationality element from the location structure
    @JsonProperty("nat")
    private String location;
    private String email;

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
