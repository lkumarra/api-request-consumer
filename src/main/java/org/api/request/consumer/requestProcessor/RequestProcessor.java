package org.api.request.consumer.requestProcessor;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.api.request.consumer.requestModal.APIRequest;

import java.util.Map;

public class RequestProcessor {
    private RequestSpecification setupRequest(APIRequest request) {
        RestAssured.baseURI = request.getUrl();
        RequestSpecification httpRequest = RestAssured.given().log().all();
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        return httpRequest;
    }

    private Response executeHttpMethod(RequestSpecification httpRequest, APIRequest request) {
        Response response;

        switch (request.getMethod().toUpperCase()) {
            case "POST":
                response = httpRequest.body(request.getBody()).post();
                break;
            case "PUT":
                response = httpRequest.body(request.getBody()).put();
                break;
            case "GET":
                response = httpRequest.get();
                break;
            case "DELETE":
                response = httpRequest.delete();
                break;
            default:
                throw new UnsupportedOperationException("Unsupported HTTP method: " + request.getMethod());
        }
        return response;
    }

    private void logResponse(Response response) {
        response.then().log().all();
    }

    public Response executeRequest(APIRequest request) {
        RequestSpecification httpRequest = setupRequest(request);
        Response response = executeHttpMethod(httpRequest, request);
        logResponse(response);
        return response;
    }


}
