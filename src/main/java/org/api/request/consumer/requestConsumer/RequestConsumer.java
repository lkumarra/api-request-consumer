package org.api.request.consumer.requestConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.api.request.consumer.helpers.PropertyReaderHelper;
import org.api.request.consumer.models.ExpectedResponseWrapper;
import org.api.request.consumer.requestModal.APIRequest;
import org.api.request.consumer.requestProcessor.RequestProcessor;
import org.api.request.consumer.responseModal.ResponseModel;
import org.api.request.consumer.responseValidor.IResponseValidator;
import org.api.request.consumer.responseValidor.ResponseValidator;
import org.api.request.consumer.responseValidor.ResponseValidatorProcessor;
import org.api.request.consumer.responseWrapper.ResponseWrapper;

import java.time.Duration;
import java.util.*;

@Slf4j
public class RequestConsumer {

    private final KafkaConsumer<String, String> consumer;
    private final RequestProcessor processor;

    public RequestConsumer() {
        PropertyReaderHelper readerHelper = new PropertyReaderHelper(System.getProperty("user.dir") + "/src/main/resources/kafka-consumer.properties");
        this.consumer = new KafkaConsumer<>(readerHelper.getProperties());
        this.processor = new RequestProcessor();
    }

    public void listenAndProcess() throws Exception {
        consumer.subscribe(Collections.singletonList("api-request"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String message = record.value();
                    log.info("Received message: {}", message);
                    ObjectMapper objectMapper = new ObjectMapper();
                    APIRequest apiRequest = objectMapper.readValue(message, APIRequest.class);
                    Response response = processor.executeRequest(apiRequest);
                    responseProcessor(response);
                }
            }
        } finally {
            consumer.close();
        }
    }

    public void responseProcessor(Response response) {
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        ResponseModel expectedResponse = new ResponseModel();
        expectedResponse.setStatusCode(200);
        ExpectedResponseWrapper expectedResponseWrapper = new ExpectedResponseWrapper();
        expectedResponseWrapper.setResponseModel(expectedResponse);
        IResponseValidator responseValidator = new ResponseValidator();
        ResponseValidatorProcessor validatorProcessor = new ResponseValidatorProcessor(responseValidator);
        validatorProcessor.validateResponse(responseWrapper, expectedResponseWrapper);
    }
}
