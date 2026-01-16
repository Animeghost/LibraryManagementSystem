package com.example.librarymanagementfrontend.http;

import java.io.IOException;

public class HttpClient {
    private final String baseUrl;
    private final HttpConnection connection;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.connection = new HttpConnection();
    }

    public void setTimeout(int milliseconds) {
        connection.setTimeout(milliseconds);
    }

    public HttpResponse get(String endpoint) throws IOException {
        System.out.println(baseUrl+endpoint);
        return connection.execute("GET", baseUrl + endpoint, null);
    }

    public HttpResponse post(String endpoint, String body) throws IOException {
        return connection.execute("POST", baseUrl + endpoint, body);
    }

    public HttpResponse put(String endpoint, String body) throws IOException {
        return connection.execute("PUT", baseUrl + endpoint, body);
    }

    public HttpResponse delete(String endpoint) throws IOException {
        return connection.execute("DELETE", baseUrl + endpoint, null);
    }
}
