package org.vasshaug.demicontt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
@Table(name="result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)
    private List<User> users;

    public Result() {
    }

    public Result(List<User> results) {
        this.users = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        Result result = (Result) o;
        return Objects.equals(id, result.id) && Objects.equals(users, result.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", users=" + users +
                '}';
    }
}
