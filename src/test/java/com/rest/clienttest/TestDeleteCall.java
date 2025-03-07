package com.rest.clienttest;

import com.rest.base.TestBase;
import com.rest.client.HTTPClientCalls;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDeleteCall extends TestBase {

    TestBase testBase;
    HTTPClientCalls driver;
    String APIURI;


    @BeforeTest
    public void setUp() {
        testBase = new TestBase();
        String URL = prop.getProperty("URI");
        String pathParam = prop.getProperty("ServiceURI");
        APIURI = URL + pathParam;
    }

    /**
     * This test will test the HTTPDelete call using HTTPClient
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testDeleteCall() throws IOException, InterruptedException {
        driver = new HTTPClientCalls();
        int response = driver.deleteUser(APIURI);
        System.out.println(response);
        Assert.assertEquals(response, RESPONSE_CODE_204);

    }
}
