package org.datafetcherserviceclient.datafetcher.controller;

import lombok.RequiredArgsConstructor;
import org.datafetcherserviceclient.datafetcher.messageBrockers.kafka.KafkaServiceA;
import org.datafetcherserviceclient.datafetcher.messageBrockers.rabbitMq.FetcherConverterProducer;
import org.datafetcherserviceclient.datafetcher.request.FetcherRequest;
import org.datafetcherserviceclient.datafetcher.service.FetcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/data-fetcher")
@RequiredArgsConstructor
public class FetcherController {

    private final FetcherService fetcherService;
    private final FetcherConverterProducer converterProducer;
    private final KafkaServiceA kafkaServiceA;

    @PostMapping("/monthly-report")
    public ResponseEntity<Map<String, Object>> getMonthlyReport(@RequestBody FetcherRequest fetcherRequest) {
        return ResponseEntity.of(Optional.ofNullable(fetcherService.fetchMonthlyReport(fetcherRequest)));
    }

    @PostMapping("/fetcher-converter")
    public ResponseEntity<String> sendAndReceiveMessageBetweenFetcherAndConverter(@RequestBody String message) {
        converterProducer.sendMessageToQueueA(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fetcher-storager")
    public ResponseEntity<String> sendAndReceiveMessageBetweenFetcherAndStorager(@RequestBody String message) {
        kafkaServiceA.sendMessageToServiceB(message);
        return ResponseEntity.ok().build();
    }

}
