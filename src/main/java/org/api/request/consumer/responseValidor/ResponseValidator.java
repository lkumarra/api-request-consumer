package org.api.request.consumer.responseValidor;

import org.api.request.consumer.models.ExpectedResponseWrapper;
import org.api.request.consumer.responseWrapper.ResponseWrapper;

public class ResponseValidator implements IResponseValidator {


    @Override
    public void validateResponse(ResponseWrapper responseModel, ExpectedResponseWrapper expectedResponseWrapper) {
        if (responseModel.getResponseModel().getStatusCode() != expectedResponseWrapper.getResponseModel().getStatusCode()) {
            assert false;
        }
    }
}
