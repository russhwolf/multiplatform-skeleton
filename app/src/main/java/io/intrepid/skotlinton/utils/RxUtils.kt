package io.intrepid.skotlinton.utils

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import timber.log.Timber

object RxUtils {

    fun logError(): Consumer<Throwable> {
        return Consumer { throwable -> Timber.w(throwable, "observable stream encountered an error") }
    }

    fun unsubscribeDisposable(disposable: Disposable?) {
        disposable?.dispose()
    }
}
