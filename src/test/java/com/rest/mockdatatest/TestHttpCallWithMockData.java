package com.rest.mockdatatest;

import com.rest.base.TestBase;
import com.rest.client.ApiServiceMockCalls;
import com.rest.client.HTTPClientCalls;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class TestHttpCallWithMockData extends TestBase {

    private HttpClient httpClient;
    private HTTPClientCalls httpClientCalls;
    private ApiServiceMockCalls apiServiceMockCalls;

    private String APIURI;
    private String parameters;


    @BeforeTest
    public void setUp() {
        String URL = prop.getProperty("URI");
        String pathParam = prop.getProperty("ServiceURI");
        parameters = prop.getProperty("Parameters");
        APIURI = URL + pathParam;
        httpClient = Mockito.mock(HttpClient.class);
        httpClientCalls = new HTTPClientCalls();
        apiServiceMockCalls = new ApiServiceMockCalls(httpClient);
    }

    /**
     * This test will mock the HTTPGet call
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testGetWithMockData() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn("{\"name\":\"Louis XV\"}");

        Mockito.when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        String result = apiServiceMockCalls.getData(APIURI);
        System.out.println(result);

        Assert.assertEquals(result, "{\"name\":\"Louis XV\"}");

    }

    /**
     * This test will mock the HTTPPost call
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testPostWithMockData() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockResponse.statusCode()).thenReturn(201);
        Mockito.when(mockResponse.body()).thenReturn("{\"id\":1,\"name\":\"Artur III\"}");

        Mockito.when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);
        String jsonPayload = "{\"name\":\"Artur III\",\"age\":30}";
        String result = apiServiceMockCalls.postData(APIURI + parameters, jsonPayload);
        System.out.println(result);

        Assert.assertEquals(result, "{\"id\":1,\"name\":\"Artur III\"}");

    }

    /**
     * This test will mock the HTTPDelete call
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testDeleteWithMockData() throws IOException, InterruptedException {
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockResponse.statusCode()).thenReturn(204);
        Mockito.when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        String result = apiServiceMockCalls.deleteData(APIURI);
        Assert.assertEquals(result, "Resource deleted successfully");
    }

}
