package com.rest.client;

public class HttpResponseData {
    private final String body;
    private final int statusCode;

    public HttpResponseData(String body, int statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "Status Code: " + statusCode + "\nResponse Body: " + body;
    }
}

