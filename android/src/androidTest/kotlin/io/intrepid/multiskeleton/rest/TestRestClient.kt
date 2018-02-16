package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.rules.MockServerRule

object TestRestClient {
    fun getRestApi(mockServer: MockServerRule): RestApi {
        return RetrofitClient.getTestApi(mockServer.serverUrl)
    }
}
