package io.intrepid.skotlinton.screens.example2

import io.intrepid.skotlinton.base.BasePresenter
import io.intrepid.skotlinton.base.PresenterConfiguration
import io.intrepid.skotlinton.utils.RxUtils
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

internal class Example2Presenter(view: Example2Contract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example2Contract.View>(view, configuration), Example2Contract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()

        disposables += restApi.getMyIp()
            .subscribeOnIoObserveOnUi()
            .subscribeBy(
                onSuccess = {
                    val ip = it.ip
                    view?.showCurrentIpAddress(ip)
                    userSettings.lastIp = ip
                },
                onError = RxUtils.logError()
            )

        val lastIp = userSettings.lastIp
        if (lastIp.isEmpty()) {
            view?.hidePreviousIpAddress()
        } else {
            view?.showPreviousIpAddress(lastIp)
        }
    }
}
