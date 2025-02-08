package org.datastorageservice.messageBrocker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceB {

    private final KafkaTemplate<String, String> kafkaTemplate;


    // Send a message to Service A
    public void sendMessageToServiceA(String message) {
        kafkaTemplate.send("service-a-topic", message);
        System.out.println("Sent message to Service A: " + message);
    }

    // Listen for messages from Service A
    @KafkaListener(topics = "service-b-topic", groupId = "service-b-group")
    public void listenFromServiceA(String message) {
        System.out.println("Received message from Service A: " + message);
        // Process the message or send a response
        sendMessageToServiceA("Acknowledged: " + message);
    }

}
