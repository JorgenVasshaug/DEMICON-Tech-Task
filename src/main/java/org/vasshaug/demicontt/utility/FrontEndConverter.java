package org.vasshaug.demicontt.utility;

import org.vasshaug.demicontt.domain.Name;
import org.vasshaug.demicontt.domain.Result;
import org.vasshaug.demicontt.frontenddomain.Country;
import org.vasshaug.demicontt.frontenddomain.User;

import java.util.*;

// Utility class for converting from randomuser JSON POJOs to the expected JSON POJOs
// Separated out into own class due to separation of concerns
public class FrontEndConverter {

    public static List<Country> convertToFrontEnd(Result result) {
        User userFrontEnd;
        org.vasshaug.demicontt.domain.User user;
        Name name;
        String fullName;
        Country country;


        Map<String, Country> countries = new HashMap<String, Country>();

        Iterator<org.vasshaug.demicontt.domain.User> userIterator = result.getResults().iterator();

        // Loop through all the results one at a time
        while ( userIterator.hasNext()) {
            user = userIterator.next();
            name = user.getName();
            fullName = name.getTitle() + " " + name.getFirst() + " " + name.getLast();
            userFrontEnd = new User(fullName, user.getGender(), user.getEmail());

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
