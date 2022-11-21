package org.vasshaug.demicontt.utility;

import org.vasshaug.demicontt.domain.Name;
import org.vasshaug.demicontt.domain.Result;
import org.vasshaug.demicontt.frontenddomain.Country;
import org.vasshaug.demicontt.frontenddomain.User;

import java.util.*;

public class FrontEndConverter {

    public static Map<String, Country> convertToFrontEnd(Result result) {
        User userFrontEnd;
        org.vasshaug.demicontt.domain.User user;
        Name name;
        String fullName;
        Country country;

        Map<String, Country> countries = new HashMap<String, Country>();

        Iterator<org.vasshaug.demicontt.domain.User> userIterator = result.getResults().iterator();

        while ( userIterator.hasNext()) {
            user = userIterator.next();
            name = user.getName();
            fullName = name.getTitle() + " " + name.getFirst() + " " + name.getLast();
            userFrontEnd = new User(fullName, user.getGender(), user.getEmail());

            if ( countries.containsKey(user.getLocation())) {
                country = countries.get(user.getLocation());
                country.addUser(userFrontEnd);
            } else {
                country = new Country(user.getLocation());
                country.addUser(userFrontEnd);
                countries.put(user.getLocation(), country);
            }
        }
        return countries;
    }

}
