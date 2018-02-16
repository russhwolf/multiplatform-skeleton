package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.models.IpModel
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {
    @GET("/?format=json")
    fun getMyIp(): Single<IpModel>
}
