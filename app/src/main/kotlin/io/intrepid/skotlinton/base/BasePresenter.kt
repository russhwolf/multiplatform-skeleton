package io.intrepid.skotlinton.base

import io.intrepid.skotlinton.logging.CrashReporter
import io.intrepid.skotlinton.rest.RestApi
import io.intrepid.skotlinton.settings.UserSettings
import io.intrepid.skotlinton.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>(protected var view: V?, configuration: PresenterConfiguration) : BaseContract.Presenter {

    protected val ioScheduler: Scheduler = configuration.ioScheduler
    protected val uiScheduler: Scheduler = configuration.uiScheduler
    protected val userSettings: UserSettings = configuration.userSettings
    protected val restApi: RestApi = configuration.restApi
    protected val crashReporter: CrashReporter = configuration.crashReporter

    protected val disposables: CompositeDisposable = CompositeDisposable()

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
        disposables.clear()
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

    fun <T> Observable<T>.subscribeOnIoObserveOnUi(): Observable<T> {
        return applySchedulers(ioScheduler, uiScheduler)
    }

    fun <T> Single<T>.subscribeOnIoObserveOnUi(): Single<T> {
        return applySchedulers(ioScheduler, uiScheduler)
    }
}
