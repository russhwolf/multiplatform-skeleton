package io.intrepid.multiskeleton.utils

import io.reactivex.disposables.Disposable
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RxUtilsTest {

    @Test
    fun unsubscribeDisposable() {
        val mockDisposable = mock(Disposable::class.java)
        RxUtils.unsubscribeDisposable(mockDisposable)
        verify(mockDisposable).dispose()

        // just ensures that there's not exception
        RxUtils.unsubscribeDisposable(null)
    }
}
