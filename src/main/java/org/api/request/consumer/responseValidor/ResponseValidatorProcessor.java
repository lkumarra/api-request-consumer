package org.api.request.consumer.responseValidor;

import org.api.request.consumer.models.ExpectedResponseWrapper;
import org.api.request.consumer.responseWrapper.ResponseWrapper;

public class ResponseValidatorProcessor {

    private final IResponseValidator responseValidator;

    public ResponseValidatorProcessor(IResponseValidator responseValidator) {
        this.responseValidator = responseValidator;
    }

    public void validateResponse(ResponseWrapper responseWrapper, ExpectedResponseWrapper expectedResponseWrapper) {
        responseValidator.validateResponse(responseWrapper, expectedResponseWrapper);
    }
}
