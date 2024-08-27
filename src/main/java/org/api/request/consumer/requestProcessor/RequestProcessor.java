package org.api.request.consumer.requestProcessor;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.api.request.consumer.requestModal.APIRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RequestProcessor {
    public String executeRequest(APIRequest request) throws Exception {
        RestAssured.baseURI = request.getUrl();
        RequestSpecification httpRequest = RestAssured.given()
                .log().all();  // Log all request details
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        Response response;
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            response = httpRequest.body(request.getBody()).post();
        } else if ("PUT".equalsIgnoreCase(request.getMethod())) {
            response = httpRequest.body(request.getBody()).put();
        } else if ("GET".equalsIgnoreCase(request.getMethod())) {
            response = httpRequest.get();
        } else if ("DELETE".equalsIgnoreCase(request.getMethod())) {
            response = httpRequest.delete();
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + request.getMethod());
        }
        response.then().log().all();
        return response.getBody().asString();
    }

}
