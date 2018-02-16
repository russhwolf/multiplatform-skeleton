package io.intrepid.multiskeleton.utils

import io.reactivex.disposables.Disposable
import timber.log.Timber

object RxUtils {

    fun logError(): (Throwable) -> Unit = { Timber.w(it, "observable stream encountered an error") }

    fun unsubscribeDisposable(disposable: Disposable?) {
        disposable?.dispose()
    }
}
