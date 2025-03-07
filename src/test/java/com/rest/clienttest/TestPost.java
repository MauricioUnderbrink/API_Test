package com.rest.clienttest;

import com.rest.base.TestBase;
import com.rest.client.HTTPClientCalls;
import com.rest.utils.TestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPost extends TestBase {

    private HTTPClientCalls driver;
    private String APIURI;
    private String jsonPayload;


    @BeforeTest
    public void setUp() {
        driver = new HTTPClientCalls();
        APIURI = prop.getProperty("URI") + prop.getProperty("ServiceURI") + prop.getProperty("Parameters");
        jsonPayload = "{\"name\": \"Marcus\"job\": \"leader\"}";

    }

    /**
     * This test will test creating a new user using the HTTPPost call using the HTTPClient
     * and will also verify the response.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void postNewUserWithHttpClient() throws IOException, InterruptedException {

        String response = driver.postNewUserWithHTTPClient(APIURI, jsonPayload);

        System.out.println(response);

        JSONObject responseObject = new JSONObject(response);
        String responseValue = TestUtils.getValueByJPath(responseObject, "/id");
        Assert.assertNotNull(responseValue);
        String dateCreated = TestUtils.getValueByJPath(responseObject, "/createdAt");
        Assert.assertNotNull(dateCreated);
    }

    /**
     * This test will test creating a new user using the HTTPPost call using the ClosableHTTPClient
     * and will also verify the response.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void postNewUsersWithClosableHttpClient() throws IOException {

        driver = new HTTPClientCalls();

        try {
            String response = driver.postNewUserWithClosableHttpClient(APIURI, jsonPayload);
            System.out.println(response);

            String[] responseValues = response.split("\n");
            Assert.assertTrue(responseValues[0].contains(String.valueOf(RESPONSE_CODE_201)));
            String[] jsonValues = responseValues[1].split(" ");
            JSONObject responseObject = new JSONObject(jsonValues[1]);
            String responseValue = TestUtils.getValueByJPath(responseObject, "/id");
            Assert.assertNotNull(responseValue);
            String dateCreated = TestUtils.getValueByJPath(responseObject, "/createdAt");
            Assert.assertNotNull(dateCreated);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
