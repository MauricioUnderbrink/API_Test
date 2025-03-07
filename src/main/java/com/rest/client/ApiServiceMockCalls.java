package com.rest.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServiceMockCalls {
    private final HttpClient httpClient;

    public ApiServiceMockCalls(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getData(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200 ? response.body() : "Error: " + response.statusCode();
    }

    public String postData(String url, String jsonPayLoad) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayLoad))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 201 ? response.body() : "Error: " + response.statusCode();
    }

    public String deleteData(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 204 ? "Resource deleted successfully" : "Error: " + response.statusCode();
    }
}
