package com.rest.restassuredtest;

import com.rest.base.TestBase;

import static io.restassured.RestAssured.*;


import com.rest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestRestAssuredCalls extends TestBase {

    private String URL;
    private String pathParam;
    private String parameters;
    private String singleUserParam;
    private String APIURI;
    private String jsonPayload;

    @BeforeTest
    public void setUp() {
        URL = prop.getProperty("URI");
        pathParam = prop.getProperty("ServiceURI");
        parameters = prop.getProperty("Parameters");
        singleUserParam = prop.getProperty("SingleUser");
        APIURI = URL + pathParam + singleUserParam;
        jsonPayload = "{\"name\": \"Martin\"job\": \"leader\"}";
    }


    /**
     * This test will test the HTTPGet using RestAssured
     */
    @Test
    public void testGetCall() {
        RestAssured.baseURI = URL;
            Response response = given()
                    .when()
                    .get(pathParam)
                    .then()
                    .assertThat().statusCode(RESPONSE_CODE_200)
                    .assertThat()
                    .extract().response();

            String responseJson2 = response.getBody().asString();
            JSONObject responseJson = new JSONObject(responseJson2);
            String firstPersonName = TestUtils.getValueByJPath(responseJson, "/data[0]/first_name");
            Assert.assertEquals(firstPersonName, "George");
            String firstPersonLastName = TestUtils.getValueByJPath(responseJson, "/data[0]/last_name");
            Assert.assertEquals(firstPersonLastName, "Bluth");
            String firstPersonEmail = TestUtils.getValueByJPath(responseJson, "/data[0]/email");
            Assert.assertEquals(firstPersonEmail, "george.bluth@reqres.in");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders());
            System.out.println("Response Body: " + response.getBody().asString());
        }


    /**
     * This test will test the creation of a new user using the HTTPPost with RestAssured
     */
    @Test
    public void testPostCall() {
        RestAssured.baseURI = URL;
        Response response = given()
                .body(jsonPayload)
                .when()
                .post(URL + pathParam + parameters)
                .then()
                .assertThat().statusCode(RESPONSE_CODE_201)
                .extract().response();
        String responseJson2 = response.getBody().asString();
        System.out.println(responseJson2);
        JSONObject responseJson = new JSONObject(responseJson2);
        String responseValue = TestUtils.getValueByJPath(responseJson, "/id");
        Assert.assertNotNull(responseValue);
        String dateCreated = TestUtils.getValueByJPath(responseJson, "/createdAt");
        Assert.assertNotNull(dateCreated);
    }

    /**
     * This test will test the Deletion using the HTTPDelete with RestAssured
     */
    @Test
    public void testDeleteCall() {
        RestAssured.baseURI = URL;
        Response response = given()
                .when()
                .delete(URL + singleUserParam)
                .then()
                .assertThat().statusCode(RESPONSE_CODE_204)
                .extract().response();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, RESPONSE_CODE_204);
    }

    /**
     * This test will test the retry mechanism with RestAssured
     */
    @Test
    public void getApiResponseWithRetryTest() {
        String url = URL + pathParam;
        String response =  TestUtils.getApiResponseWithRetry(url, 3 );
        JSONObject responseJson = new JSONObject(response);
        String firstPersonName = TestUtils.getValueByJPath(responseJson, "/data[0]/first_name");
        Assert.assertEquals(firstPersonName, "George");
        String firstPersonLastName = TestUtils.getValueByJPath(responseJson, "/data[0]/last_name");
        Assert.assertEquals(firstPersonLastName, "Bluth");
        String firstPersonEmail = TestUtils.getValueByJPath(responseJson, "/data[0]/email");
        Assert.assertEquals(firstPersonEmail, "george.bluth@reqres.in");


    }

}
