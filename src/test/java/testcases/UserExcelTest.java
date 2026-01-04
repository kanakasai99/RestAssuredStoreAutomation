package testcases;

import endpoints.EndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pojo.User;
import utils.DataProviders;

import static io.restassured.RestAssured.*;

public class UserExcelTest extends BaseClass {
    private static final Logger log =
            Logger.getLogger(UserExcelTest.class);
    @Test(
            dataProvider = "userExcelData",
            dataProviderClass = DataProviders.class
    )
    public void createUserTest(User user) {
        log.info("Starting product creation test");
        log.info("data received from Excel:");
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .statusCode(201)
                .log().all();
    }
}
