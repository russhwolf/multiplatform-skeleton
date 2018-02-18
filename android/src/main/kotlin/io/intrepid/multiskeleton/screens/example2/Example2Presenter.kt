package io.intrepid.multiskeleton.screens.example2

import io.intrepid.multiskeleton.base.BasePresenter
import io.intrepid.multiskeleton.base.PresenterConfiguration
import io.intrepid.multiskeleton.models.IpModel
import io.intrepid.multiskeleton.rest.Call
import io.intrepid.multiskeleton.rest.Callback
import io.intrepid.multiskeleton.rest.Response

internal class Example2Presenter(view: Example2Contract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example2Contract.View>(view, configuration), Example2Contract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()

        restApi.getMyIp().enqueue(object : Callback<IpModel> {
            override fun onFailure(call: Call<IpModel>, t: Throwable) {
                logger.e(t, "Exception during retrofit call!")
            }

            override fun onResponse(call: Call<IpModel>, response: Response<IpModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val ip = it.ip
                        view?.showCurrentIpAddress(ip)
                        userSettings.lastIp = ip
                    } ?: logger.w("Null response body!")
                } else {
                    logger.w(response.errorBody().toString())
                }
            }

        })

        val lastIp = userSettings.lastIp
        if (lastIp.isEmpty()) {
            view?.hidePreviousIpAddress()
        } else {
            view?.showPreviousIpAddress(lastIp)
        }
    }
}
