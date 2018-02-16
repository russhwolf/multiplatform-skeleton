package io.intrepid.multiskeleton.screens.example2

import io.intrepid.multiskeleton.base.BasePresenter
import io.intrepid.multiskeleton.base.PresenterConfiguration
import io.intrepid.multiskeleton.models.IpModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

internal class Example2Presenter(view: Example2Contract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example2Contract.View>(view, configuration), Example2Contract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()

        restApi.getMyIp().enqueue(object : Callback<IpModel> {
            override fun onFailure(call: Call<IpModel>, t: Throwable) {
                Timber.e(t, "Exception during retrofit call!")
            }

            override fun onResponse(call: Call<IpModel>, response: Response<IpModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val ip = it.ip
                        view?.showCurrentIpAddress(ip)
                        userSettings.lastIp = ip
                    } ?: Timber.w("Null response body!")
                } else {
                    Timber.w(response.errorBody().toString())
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
