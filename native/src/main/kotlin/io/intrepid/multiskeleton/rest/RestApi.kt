package io.intrepid.multiskeleton.rest

import io.intrepid.multiskeleton.models.IpModel

actual interface RestApi {
    actual fun getMyIp(): Call<IpModel>
}

