package io.intrepid.skotlinton.rest

import io.intrepid.skotlinton.models.IpModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    val myIp: Observable<IpModel>
}
