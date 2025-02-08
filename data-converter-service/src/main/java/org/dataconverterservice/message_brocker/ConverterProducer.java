package org.dataconverterservice.message_brocker;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageToQueueB(String message) {
        rabbitTemplate.convertAndSend("From ProducerB: " + message);
    }

}
