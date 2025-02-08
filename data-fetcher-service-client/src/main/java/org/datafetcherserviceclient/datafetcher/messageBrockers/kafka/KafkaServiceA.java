package org.datafetcherserviceclient.datafetcher.messageBrockers.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceA {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToServiceB(String message) {
        kafkaTemplate.send("service-b-topic", message);
        System.out.println("Message sent to service B: " + message);
    }

    @KafkaListener(topics = "service-a-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void listenFromServiceB(String message) {
        System.out.println("Received message from service B: " + message);
    }

}
