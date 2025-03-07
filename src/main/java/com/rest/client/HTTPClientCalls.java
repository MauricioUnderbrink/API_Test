package com.rest.client;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClientCalls {

    private final CloseableHttpClient httpClient;

    public HTTPClientCalls() {
        this.httpClient = HttpClients.createDefault();
    }

    public CloseableHttpResponse getCall(String url) throws IOException {

        HttpGet getCall = new HttpGet(url);
        return httpClient.execute(getCall);

    }


    public String postNewUserWithHTTPClient(String url, String jsonPayLoad) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                //.header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayLoad))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String postNewUserWithClosableHttpClient(String url, String jsonPayLoad) throws IOException {

        HttpPost postCall = new HttpPost(url);
        postCall.setEntity(new StringEntity(jsonPayLoad));
        try (CloseableHttpResponse response = httpClient.execute(postCall)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            return "Status_Code: " + statusCode + "\nResponse_Body: " + responseBody;
        } finally {
            httpClient.close();
        }
    }

    public int deleteUser(String url) throws IOException {

        HttpDelete deleteCall = new HttpDelete(url);

        try (CloseableHttpResponse response = httpClient.execute(deleteCall)) {
            return response.getStatusLine().getStatusCode();
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } finally {
            httpClient.close();
        }

    }

}
