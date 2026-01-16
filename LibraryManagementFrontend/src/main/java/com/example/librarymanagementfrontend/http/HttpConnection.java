package com.example.librarymanagementfrontend.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpConnection {
    private int timeout = 10000; // 10 sec

    public void setTimeout(int milliseconds) {
        this.timeout = milliseconds;
    }

    public HttpResponse execute(String method, String urlString, String body) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            conn.setRequestMethod(method);
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            if (body != null && !body.isEmpty()) {
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = body.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            int statusCode = conn.getResponseCode();
            String responseBody = readResponse(conn, statusCode);

            return new HttpResponse(statusCode, responseBody);

        } finally {
            conn.disconnect();
        }
    }

    private String readResponse(HttpURLConnection conn, int statusCode) throws IOException {
        InputStream is = statusCode < 400 ? conn.getInputStream() : conn.getErrorStream();

        if (is == null) return "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
