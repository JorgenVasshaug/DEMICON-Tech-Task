package org.vasshaug.demicontt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id")
    private Name name;

    @Column(name="gender")
    private String gender;

    // In the call on the randomuser API we specify that we only want the nationality element from the location structure
    @JsonProperty("nat")
    @Column(name="location")
    private String location;
    @Column(name="email")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Result result;

    public User() {
    }

    public User(String gender, String location, String email) {
        this.gender = gender;
        this.location = location;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(gender, user.gender) && Objects.equals(location, user.location) && Objects.equals(email, user.email) && Objects.equals(result, user.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, location, email, result);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", result=" + result +
                '}';
    }
}
