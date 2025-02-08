package org.datafetcherserviceclient.datafetcher.service;

import lombok.RequiredArgsConstructor;
import org.datafetcherserviceclient.datafetcher.constants.FetcherConstants;
import org.datafetcherserviceclient.datafetcher.messageBrockers.rabbitMq.FetcherConverterProducer;
import org.datafetcherserviceclient.datafetcher.request.FetcherRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FetcherService {

    private final FetcherConverterProducer fetcherConverterProducer;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> fetchMonthlyReport(FetcherRequest request) {
        long fromDate = LocalDate.now().minusMonths(1).withDayOfMonth(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        long toDate = LocalDate.now().withDayOfMonth(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        String url = FetcherConstants.API_URL + request.getAccountId() + "/" + fromDate + "/" + toDate;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Token", request.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        /*1. sendMessageToDataConvertor;
          2. recieveMessageFromDataConvertor
          3. sendMessageToDataSaver
          4. recieveMessageFromDataSaver*/
        
        return makeApiCall(url, entity);

    }

    private Map<String, Object> makeApiCall(String url, HttpEntity<String> entity) {
        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            System.out.println("Raw Response: " + response.getBody());

            return Map.of(
                    "status", "success",
                    "data", response.getBody()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of(
                    "status", "error",
                    "message", e.getMessage()
            );
        }
    }

}
