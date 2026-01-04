package utils;

import pojo.*;

import java.util.Map;

public class UserBuilder {

    public static User buildUser(Map<String, String> data) {

        Name name = new Name(
                data.get("firstname"),
                data.get("lastname")
        );

        Geolocation geo = new Geolocation(
                data.get("lat"),
                data.get("long")
        );

        Address address = new Address(
                data.get("city"),
                data.get("street"),
                Integer.parseInt(data.get("number")),
                data.get("zipcode"),
                geo
        );

        return new User(
                data.get("email"),
                data.get("username"),
                data.get("password"),
                name,
                address,
                data.get("phone")
        );
    }
}
