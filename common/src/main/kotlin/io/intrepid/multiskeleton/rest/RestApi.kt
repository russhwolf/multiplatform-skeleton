package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.models.IpModel

expect interface RestApi {
    fun getMyIp(): Call<IpModel>
}

expect interface Call<T> {
    fun enqueue(callback: Callback<T>)
    fun isExecuted(): Boolean
    fun cancel()
    fun isCanceled(): Boolean
}

expect interface Callback<T>


