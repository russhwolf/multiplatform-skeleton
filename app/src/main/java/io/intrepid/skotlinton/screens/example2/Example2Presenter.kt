package io.intrepid.skotlinton.screens.example2

import io.intrepid.skotlinton.base.BasePresenter
import io.intrepid.skotlinton.base.PresenterConfiguration
import io.intrepid.skotlinton.utils.RxUtils
import io.reactivex.disposables.Disposable

class Example2Presenter internal constructor(view: Example2Contract.View,
                                             configuration: PresenterConfiguration) : BasePresenter<Example2Contract.View>(view, configuration), Example2Contract.Presenter {

    override fun onViewCreated() {
        val disposable = restApi.myIp
                .compose<IpModel>(subscribeOnIoObserveOnUi<IpModel>())
                .subscribe({ ipModel ->
                    val ip = ipModel.ip
                    view.showCurrentIpAddress(ip)
                    userSettings.lastIp = ip
                }, RxUtils.logError())
        disposables.add(disposable)

        val lastIp = userSettings.lastIp
        if (lastIp == null) {
            view.hidePreviousIpAddress()
        } else {
            view.showPreviousIpAddress(lastIp)
        }
    }
}
