package org.api.request.consumer.responseValidor;

import org.api.request.consumer.models.ExpectedResponseWrapper;
import org.api.request.consumer.responseModal.ResponseModel;
import org.api.request.consumer.responseWrapper.ResponseWrapper;

public interface IResponseValidator {

    void validateResponse(ResponseWrapper responseModel, ExpectedResponseWrapper responseWrapper);
}
