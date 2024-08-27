package org.api.request.consumer.responseWrapper;

import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.Data;
import org.api.request.consumer.responseModal.ResponseModel;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseWrapper {

    private Response response;

    public ResponseWrapper(Response response) {
        this.response = response;
    }

    public ResponseModel getResponseModel() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatusCode(response.getStatusCode());
        responseModel.setResponseBody(response.getBody().asString());
        Map<String, String> headerMap = new HashMap<>();
        for (Header header : response.headers()) {
            headerMap.put(header.getName(), header.getValue());
        }
        responseModel.setHeaders(headerMap);
        responseModel.setResponseTime(response.time());
        return responseModel;
    }
}
