package org.api.request.consumer.responseModal;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseModel {

    private int statusCode;
    private String responseBody;
    private Map<String, String> headers;
    private long responseTime;
}
