package com.rest.clienttest;

import com.rest.base.TestBase;
import com.rest.client.HTTPClientCalls;
import com.rest.utils.TestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestGetCall extends TestBase {


    private HTTPClientCalls driver;
    private String APIURI;
    private CloseableHttpResponse apiResponse;

    @BeforeTest
    public void setUp() {
        APIURI = prop.getProperty("URI") + prop.getProperty("ServiceURI");
    }

    /**
     * This test will test the HTTPGet call and will verify the statusCode.
     *
     * @throws IOException
     */
    @Test
    public void testGetCall() throws IOException {
        driver = new HTTPClientCalls();
        apiResponse = driver.getCall(APIURI);
        int statusCode = apiResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_CODE_200);

    }

    /**
     * This test will get the data from the HTTPGet call and verify the first user's data
     *
     * @throws IOException
     */
    @Test
    public void testGetCallResponse() throws IOException {
        driver = new HTTPClientCalls();
        apiResponse = driver.getCall(APIURI);
        String responseString = EntityUtils.toString(apiResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String responseValue = TestUtils.getValueByJPath(responseJson, "/per_page");
        Assert.assertEquals(responseValue, "6");
        String firstPersonName = TestUtils.getValueByJPath(responseJson, "/data[0]/first_name");
        Assert.assertEquals(firstPersonName, "George");
        String firstPersonLastName = TestUtils.getValueByJPath(responseJson, "/data[0]/last_name");
        Assert.assertEquals(firstPersonLastName, "Bluth");
        String firstPersonEmail = TestUtils.getValueByJPath(responseJson, "/data[0]/email");
        Assert.assertEquals(firstPersonEmail, "george.bluth@reqres.in");
    }

    /**
     * This test will get the data from the HTTPGet call and verify the second user's data
     *
     * @throws IOException
     */
    @Test
    public void testSecondIndex() throws IOException {
        driver = new HTTPClientCalls();
        apiResponse = driver.getCall(APIURI);
        String responseString = EntityUtils.toString(apiResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String responseValue = TestUtils.getValueByJPath(responseJson, "/per_page");
        Assert.assertEquals(responseValue, "6");
        String id = TestUtils.getValueByJPath(responseJson, "/data[1]/id");
        Assert.assertEquals(id, "2");
        String firstPersonName = TestUtils.getValueByJPath(responseJson, "/data[1]/first_name");
        Assert.assertEquals(firstPersonName, "Janet");
        String firstPersonLastName = TestUtils.getValueByJPath(responseJson, "/data[1]/last_name");
        Assert.assertEquals(firstPersonLastName, "Weaver");
        String firstPersonEmail = TestUtils.getValueByJPath(responseJson, "/data[1]/email");
        Assert.assertEquals(firstPersonEmail, "janet.weaver@reqres.in");
    }

    /**
     * This test will get the data from the HTTPGet call and verify the third user's data
     *
     * @throws IOException
     */
    @Test
    public void testThirdIndex() throws IOException {
        driver = new HTTPClientCalls();
        apiResponse = driver.getCall(APIURI);
        String responseString = EntityUtils.toString(apiResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String responseValue = TestUtils.getValueByJPath(responseJson, "/per_page");
        Assert.assertEquals(responseValue, "6");
        String id = TestUtils.getValueByJPath(responseJson, "/data[3]/id");
        Assert.assertEquals(id, "4");
        String firstPersonName = TestUtils.getValueByJPath(responseJson, "/data[3]/first_name");
        Assert.assertEquals(firstPersonName, "Eve");
        String firstPersonLastName = TestUtils.getValueByJPath(responseJson, "/data[3]/last_name");
        Assert.assertEquals(firstPersonLastName, "Holt");
        String firstPersonEmail = TestUtils.getValueByJPath(responseJson, "/data[3]/email");
        Assert.assertEquals(firstPersonEmail, "eve.holt@reqres.in");
    }


}
