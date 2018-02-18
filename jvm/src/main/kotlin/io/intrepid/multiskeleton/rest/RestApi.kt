package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.models.IpModel
import retrofit2.http.GET

actual interface RestApi {
    @GET("/?format=json")
    actual fun getMyIp(): Call<IpModel>
}

actual typealias Call<T> = retrofit2.Call<T>
actual typealias Callback<T> = retrofit2.Callback<T>
