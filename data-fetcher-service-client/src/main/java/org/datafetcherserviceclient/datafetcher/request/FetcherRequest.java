package org.datafetcherserviceclient.datafetcher.request;

import lombok.Data;

@Data
public class FetcherRequest {
    private String accountId;
    private String accessToken;
}
