package testcases;

import endpoints.EndPoints;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojo.*;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserJsonDdt extends BaseClass
{

    @Test(dataProvider = "jsonDataUser",
            dataProviderClass = utils.DataProviders.class)
    public void testAddNewUser(Map<String, Object> data) {

        String email = (String) data.get("email");
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        String phone = (String) data.get("phone");

        // name object
        Map<String, String> nameMap =
                (Map<String, String>) data.get("name");

        Name name = new Name(
                nameMap.get("firstname"),
                nameMap.get("lastname")
        );

        // address object
        Map<String, Object> addressMap =
                (Map<String, Object>) data.get("address");

        Map<String, String> geoMap =
                (Map<String, String>) addressMap.get("geolocation");

        Geolocation geo = new Geolocation(
                geoMap.get("lat"),
                geoMap.get("long")
        );

        Address address = new Address(
                (String) addressMap.get("city"),
                (String) addressMap.get("street"),
                (Integer) addressMap.get("number"),
                (String) addressMap.get("zipcode"),
                geo
        );

        User newUser = new User(email, username, password, name, address, phone);

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .statusCode(201);
    }
    @Test(dataProvider = "jsonDataUser1",
            dataProviderClass = utils.DataProviders.class)
    public void testAddNewUser1(User user) {

        given()
                .contentType(ContentType.JSON)
                .body(user)          // POJO → JSON automatically
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue());
    }


}
