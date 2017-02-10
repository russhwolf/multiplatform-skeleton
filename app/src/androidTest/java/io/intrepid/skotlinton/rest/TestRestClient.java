package io.intrepid.skotlinton.rest;

import io.intrepid.skotlinton.rules.MockServerRule;

public class TestRestClient {
    public static RestApi getRestApi(MockServerRule mockServer) {
        return RetrofitClient.getTestApi(mockServer.getServerUrl());
    }
}
