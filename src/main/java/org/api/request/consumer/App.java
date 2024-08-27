package org.api.request.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.request.consumer.requestConsumer.RequestConsumer;
import org.api.request.consumer.requestModal.APIRequest;
import org.api.request.consumer.requestProcessor.RequestProcessor;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        RequestConsumer consumer = new RequestConsumer();
        consumer.listenAndProcess();
    }
}
