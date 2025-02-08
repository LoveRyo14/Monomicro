package org.datafetcherserviceclient.datafetcher.messageBrockers.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FetcherConverterConsumer {

    @RabbitListener(queues = "converterToFetcherQueue")
    public void receiveMessageFromQueueB(String message) {
        System.out.println("Microservice A received: " + message);
    }

}
