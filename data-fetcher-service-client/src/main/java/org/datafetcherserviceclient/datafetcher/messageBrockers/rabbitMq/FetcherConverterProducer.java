package org.datafetcherserviceclient.datafetcher.messageBrockers.rabbitMq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FetcherConverterProducer {

    public final RabbitTemplate rabbitTemplate;

    public void sendMessageToQueueA(String message) {
        rabbitTemplate.convertAndSend("fetcherToConverterQueue", message);

        System.out.println("Has been sent: " + message);
    }

}
