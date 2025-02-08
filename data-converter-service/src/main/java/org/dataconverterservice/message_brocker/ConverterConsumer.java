package org.dataconverterservice.message_brocker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConverterConsumer {

    @RabbitListener(queues = "fetcherToConverterQueue")
    public void receiveMessageFromQueueA(String message) {
        System.out.println("Microservice B received: " + message);
    }

}
