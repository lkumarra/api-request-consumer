package org.api.request.consumer;

import org.api.request.consumer.requestConsumer.RequestConsumer;

public class App {
    public static void main(String[] args) throws Exception {
        RequestConsumer consumer = new RequestConsumer();
        consumer.listenAndProcess();
    }
}
