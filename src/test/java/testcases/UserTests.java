package testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.Payload;
import pojo.User;
import endpoints.EndPoints;


public class UserTests extends BaseClass
{
    private static final Logger log =
            Logger.getLogger(UserTests.class);

    //1) Fetch all the users
    @Test
    public void testGetAllUsers()
    {
        log.info("get all users started");
        given()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .log().body()
                .contentType(ContentType.JSON)
                .body("size()",greaterThan(0));

    }

    //2)  Test to fetch a specific user by ID
    @Test
    public void testGetUserById()
    {
        log.info("get all users by id started");
        int userId=configReader.getIntProperty("userId");
        given()
                .pathParam("id",userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then()
                .log().body()
                .statusCode(200);
    }


    //3) Test to fetch a limited number of users
    @Test
    public void testGetUsersWithLimit()
    {
        log.info("get all users with limit started");
        int limit=configReader.getIntProperty("limit");
        given()
                .pathParam("limit",limit)
                .when()
                .get(EndPoints.GET_USERS_WITH_LIMIT)
                .then()
                .statusCode(200)
                .log().body()
                .body("size()",equalTo(limit));

    }

    //4) Test to fetch users sorted in descending order
    @Test
    void testGetUsersSorted()
    {
        log.info("get all users sorted started");
        Response response=given()
                .pathParam("order", "desc")
                .when()
                .get(EndPoints.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> userIds=response.jsonPath().getList("id", Integer.class);


        assertThat(isSortedDescending(userIds), is(true));
    }

    //5) Test to fetch users sorted in ascending order
    @Test
    void testGetUsersSortedAsc()
    {
        Response response=given()
                .pathParam("order", "asc")
                .when()
                .get(EndPoints.GET_USERS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> userIds=response.jsonPath().getList("id", Integer.class);


        assertThat(isSortedAscending(userIds), is(true));
    }



    //6) Test to create a new user

    @Test
    public void testCreateUser()
    {
        log.info("create users started");
        User newUser=Payload.userPayload();

        int id=given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");

        System.out.println("Generated UserID=====:"+ id);

    }

    //7) Test to update user

    @Test
    public void testUpdateUser()
    {
        int userId=configReader.getIntProperty("userId");

        User updateUser=Payload.userPayload();

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .body(updateUser)
                .when()
                .put(EndPoints.UPDATE_USER)
                .then()
                .log().body()
                .statusCode(200)
                .body("username",equalTo(updateUser.getUsername()));

    }

    //8) delete user

    @Test
    void testDeleteUser()
    {

        int userId=configReader.getIntProperty("userId");

        given()
                .pathParam("id", userId)
                .when()
                .delete(EndPoints.DELETE_USER)
                .then()
                .statusCode(200);
    }




}
