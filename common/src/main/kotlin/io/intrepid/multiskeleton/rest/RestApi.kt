package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.models.IpModel

expect interface RestApi {
    fun getMyIp(): Call<IpModel>
}

