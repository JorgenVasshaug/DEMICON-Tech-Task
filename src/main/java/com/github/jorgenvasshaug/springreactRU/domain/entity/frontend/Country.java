package com.github.jorgenvasshaug.springreactRU.domain.entity.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Country {
    private String name;
    List<User> users = new ArrayList<User>();

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) && Objects.equals(users, country.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
