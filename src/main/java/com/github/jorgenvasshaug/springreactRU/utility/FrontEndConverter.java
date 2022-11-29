package com.github.jorgenvasshaug.springreactRU.utility;

import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.User;
import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.Name;
import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.Result;
import com.github.jorgenvasshaug.springreactRU.domain.entity.frontend.Country;

import java.util.*;

// Utility class for converting from randomuser JSON POJOs to the expected JSON POJOs
// Separated out into own class due to separation of concerns
public class FrontEndConverter {

    public static List<Country> convertToFrontEnd(Result result) {
        com.github.jorgenvasshaug.springreactRU.domain.entity.frontend.User userFrontEnd;
        User user;
        Name name;
        String fullName;
        Country country;


        Map<String, Country> countries = new HashMap<String, Country>();

        Iterator<User> userIterator = result.getResults().iterator();

        // Loop through all the results one at a time
        while ( userIterator.hasNext()) {
            user = userIterator.next();
            name = user.getName();
            fullName = name.getTitle() + " " + name.getFirst() + " " + name.getLast();
            userFrontEnd = new com.github.jorgenvasshaug.springreactRU.domain.entity.frontend.User(fullName, user.getGender(), user.getEmail());

            if ( countries.containsKey(user.getLocation())) {
                // If country exists in Map, add user to its list
                country = countries.get(user.getLocation());
                country.addUser(userFrontEnd);
            } else {
                // Country does not exist, create it and add user
                country = new Country(user.getLocation());
                country.addUser(userFrontEnd);
                countries.put(user.getLocation(), country);
            }
        }

        // Return only values from the map, the key is not interesting
        return new ArrayList<Country>(countries.values());
    }



}
