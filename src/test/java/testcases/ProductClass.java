package testcases;

import endpoints.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.Payload;
import pojo.Product;


import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ProductClass extends BaseClass{
    @Test
    public void getAllProducts(){
        given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .log().body();
    }

    @Test()
    public void getWithproductId(){
        int productId=configReader.getIntProperty("productId");
        given()
                .pathParam("id",productId)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then()
                .log().body();

        }
@Test()
    public void getProductsWithLimit(){
        given()
                .pathParam("limit",3)
                .when()
                .get(EndPoints.GET_PRODUCTS_WITH_LIMIT)
                .then()
                .statusCode(200)
                .log().body()
                .body("size()",equalTo(3));
    }
@Test()
    public void getProductSorted(){
       Response response= given()
                .pathParam("order","desc")
                .when()
                .get(EndPoints.GET_PRODUCTS_SORTED)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
      List<Integer> productIds= response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedDescending(productIds),is(true));
    }

    @Test()
    public void getProductSortedAsc(){
        Response response= given()
                .pathParam("order","asc")
                .when()
                .get(EndPoints.GET_PRODUCTS_SORTED)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        List<Integer> productIds= response.jsonPath().getList("id", Integer.class);
        assertThat(isSortedAscending(productIds),is(true));
    }
    //6) Test to get all product categories
    @Test
    public void testGetAllCategories()
    {
        given()

                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0));

    }
    //7) Test to get products by category

    @Test
    public void testGetProductsByCategory()
    {
        given()
                .pathParam("category", "electronics")

                .when()
                .get(EndPoints.GET_PRODUCTS_BY_CATEGORY)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("category", everyItem(notNullValue()))
                .body("category", everyItem(equalTo("electronics")))
                .log().body();

    }


    //8) Test to add a new product
    @Test
    public void testAddNewProduct()
    {
        Product newProduct=Payload.productPayload();


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

        System.out.println(productId);

    }

    //9) Test to update an existing product
    @Test
    public void testUpdateProduct()
    {
        int productId=configReader.getIntProperty("productId");

        Product updatedPayload= Payload.productPayload();

        given()
                .contentType(ContentType.JSON)
                .body(updatedPayload)
                .pathParam("id", productId)

                .when()
                .put(EndPoints.UPDATE_PRODUCT)
                .then()
                .log().body()
                .statusCode(200)
                .body("title", equalTo(updatedPayload.getTitle()));

    }

    //10) test to delete a product
    @Test
    public void testDeleteProduct()
    {
        int productId=configReader.getIntProperty("productId");

        given()
                .pathParam("id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT)
                .then()
                .statusCode(200);
    }

}





