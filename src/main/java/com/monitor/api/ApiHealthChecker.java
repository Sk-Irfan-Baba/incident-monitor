package com.monitor.api;

import com.monitor.db.IncidentDAO;

import java.net.URI;
import java.net.http.*;

public class ApiHealthChecker {

    public static void check(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                IncidentDAO.save("API_DOWN",
                        "Status: " + response.statusCode());
            }
        } catch (Exception e) {
            IncidentDAO.save("API_EXCEPTION", e.getMessage());
        }
    }
}
