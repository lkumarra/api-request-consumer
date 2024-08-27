package org.api.request.consumer.models;

import lombok.Data;
import org.api.request.consumer.responseModal.ResponseModel;

@Data
public class ExpectedResponseWrapper {

    private ResponseModel responseModel;
}
