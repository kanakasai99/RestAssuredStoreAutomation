package testcases;

import endpoints.EndPoints;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import utils.DataProviders;
import org.apache.log4j.Logger;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ProductExcelTest extends BaseClass {
    // ✅ 1. Logger at class level
    private static final Logger log =
            Logger.getLogger(ProductExcelTest.class);

    // ✅ 2. Test method with DataProvider
    @Test(
            dataProvider = "excelData",
            dataProviderClass = DataProviders.class
    )
    public void createProductTest(Map<String, String> payload) {

        // ✅ 3. Logging inside method
        log.info("Starting product creation test");
        log.info("Payload received from Excel: " + payload);
        given()
                .contentType(ContentType.JSON)
                .body(payload)   // Excel row → Map → JSON
                .when()
                .post(EndPoints.CREATE_PRODUCT)
                .then()
                .statusCode(201)   // fakestoreapi returns 200
                .log().all();
    }
}

