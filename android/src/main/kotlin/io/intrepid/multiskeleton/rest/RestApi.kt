package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.models.IpModel
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {
    @GET("/?format=json")
    fun getMyIp(): Call<IpModel>
}
