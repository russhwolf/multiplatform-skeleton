package io.intrepid.skotlinton.rest

import io.intrepid.skotlinton.rules.MockServerRule

object TestRestClient {
    fun getRestApi(mockServer: MockServerRule): RestApi {
        return RetrofitClient.getTestApi(mockServer.serverUrl)
    }
}
