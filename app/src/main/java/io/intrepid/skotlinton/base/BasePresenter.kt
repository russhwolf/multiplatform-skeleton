package io.intrepid.skotlinton.base

import io.intrepid.skotlinton.logging.CrashReporter
import io.intrepid.skotlinton.rest.RestApi
import io.intrepid.skotlinton.settings.UserSettings
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : BaseContract.View>(protected var view: T?, configuration: PresenterConfiguration) : BaseContract.Presenter<T> {

    protected val disposables = CompositeDisposable()
    protected val ioScheduler: Scheduler
    protected val uiScheduler: Scheduler
    protected val userSettings: UserSettings
    protected val restApi: RestApi
    protected val crashReporter: CrashReporter

    private var isViewBound = false

    init {
        this.ioScheduler = configuration.ioScheduler
        this.uiScheduler = configuration.uiScheduler
        this.userSettings = configuration.userSettings
        this.restApi = configuration.restApi
        this.crashReporter = configuration.crashReporter
    }

    override fun onViewCreated() {

    }

    override fun bindView(view: T) {
        this.view = view

        if (!isViewBound) {
            onViewBound()
            isViewBound = true
        }
    }

    protected fun onViewBound() {

    }

    override fun unbindView() {
        disposables.clear()
        this.view = null

        if (isViewBound) {
            onViewUnbound()
            isViewBound = false
        }
    }

    protected fun onViewUnbound() {

    }

    /**
     * Note: The view will be null at this point. This method is for any additional cleanup that's not handled
     * by the CompositeDisposable
     */
    override fun onViewDestroyed() {

    }

    protected fun <R> subscribeOnIoObserveOnUi(): ObservableTransformer<R, R> {
        return { observable -> observable.subscribeOn(ioScheduler).observeOn(uiScheduler) }
    }
}
