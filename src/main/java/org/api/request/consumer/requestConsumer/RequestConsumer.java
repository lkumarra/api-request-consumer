package org.api.request.consumer.requestConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.api.request.consumer.helpers.PropertyReaderHelper;
import org.api.request.consumer.requestModal.APIRequest;
import org.api.request.consumer.requestProcessor.RequestProcessor;

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
                    processor.executeRequest(apiRequest);
                }
            }
        } finally {
            consumer.close();
        }
    }

    public List<APIRequest> consumeMessages() throws Exception {
        consumer.subscribe(Collections.singletonList("api-request"));
        List<APIRequest> apiRequests = new ArrayList<>();
        // Poll for messages
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
        if (records.isEmpty()) {
            System.out.println("No messages found in the topic.");
            return apiRequests;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        for (ConsumerRecord<String, String> record : records) {
            // Convert JSON message to APIRequest object and add to list
            APIRequest apiRequest = objectMapper.readValue(record.value(), APIRequest.class);
            apiRequests.add(apiRequest);
            // Manually commit the offset to "delete" the message after consuming
            consumer.commitSync(Collections.singletonMap(
                    new TopicPartition(record.topic(), record.partition()),
                    new org.apache.kafka.clients.consumer.OffsetAndMetadata(record.offset() + 1)
            ));
        }
        return apiRequests;
    }
}
