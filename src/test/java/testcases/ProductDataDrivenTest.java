package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pojo.Product;
import endpoints.EndPoints;

public class ProductDataDrivenTest extends BaseClass
{

    @Test(dataProvider="jsonDataProvider", dataProviderClass=utils.DataProviders.class)
    public void testAddNewProduct(Map<String,String> data)
    {

        String title=data.get("title");
        double price=Double.parseDouble(data.get("price"));
        String category=data.get("category");
        String description=data.get("description");
        String image=data.get("image");

        //String title, double price, String description, String image, String category
        Product newProduct=new Product(title,price,description,image,category);


        int productId=given()
                .contentType(ContentType.JSON)
                .body(newProduct)

                .when()
                .post(EndPoints.CREATE_PRODUCT)
                .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(newProduct.getTitle()))
                .extract().jsonPath().getInt("id"); //Extracting Id from response body

        System.out.println("Product ID======> "+ productId);

        //Delete product
        given()
                .pathParam("id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT)
                .then()
                .statusCode(200);

        System.out.println("Deleted Product ID======> "+ productId);
    }

}