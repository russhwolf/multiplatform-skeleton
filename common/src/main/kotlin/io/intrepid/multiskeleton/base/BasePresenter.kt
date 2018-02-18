package io.intrepid.multiskeleton.base

import io.intrepid.multiskeleton.logging.CrashReporter
import io.intrepid.multiskeleton.rest.RestApi
import io.intrepid.multiskeleton.settings.UserSettings

abstract class BasePresenter<V : BaseContract.View>(protected var view: V?, configuration: PresenterConfiguration) : BaseContract.Presenter {

    protected val userSettings: UserSettings = configuration.userSettings
    protected val restApi: RestApi = configuration.restApi
    protected val crashReporter: CrashReporter = configuration.crashReporter

    private var isViewBound = false

    override fun onViewCreated() {

    }

    override fun bindView(view: BaseContract.View) {
        @Suppress("UNCHECKED_CAST")
        this.view = view as V
        if (!isViewBound) {
            onViewBound()
            isViewBound = true
        }
    }

    open protected fun onViewBound() {

    }

    override fun unbindView() {
        this.view = null

        if (isViewBound) {
            onViewUnbound()
            isViewBound = false
        }
    }

    open protected fun onViewUnbound() {

    }

    /**
     * Note: The view will be null at this point. This method is for any additional cleanup that's not handled
     * by the CompositeDisposable
     */
    override fun onViewDestroyed() {

    }
}
