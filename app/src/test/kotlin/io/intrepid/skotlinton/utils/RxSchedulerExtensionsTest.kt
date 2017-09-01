package io.intrepid.skotlinton.utils

import io.reactivex.*
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

class RxSchedulerExtensionsTest {

    private val ioScheduler = TestScheduler()
    private val uiScheduler = TestScheduler()

    @Test
    fun observableApply() {
        val testObserver = Observable.just(5).applySchedulers(ioScheduler, uiScheduler).test()
        testObserver.assertNoValues()
        ioScheduler.triggerActions()
        testObserver.assertNoValues()
        uiScheduler.triggerActions()
        testObserver.assertValue(5)
        testObserver.assertComplete()
    }

    @Test
    fun flowableApply() {
        val testObserver = Flowable.just(5).applySchedulers(ioScheduler, uiScheduler).test()
        testObserver.assertNoValues()
        ioScheduler.triggerActions()
        testObserver.assertNoValues()
        uiScheduler.triggerActions()
        testObserver.assertValue(5)
        testObserver.assertComplete()
    }

    @Test
    fun singleApply() {
        val testObserver = Single.just(5).applySchedulers(ioScheduler, uiScheduler).test()
        testObserver.assertNoValues()
        ioScheduler.triggerActions()
        testObserver.assertNoValues()
        uiScheduler.triggerActions()
        testObserver.assertValue(5)
        testObserver.assertComplete()
    }

    @Test
    fun completableApply() {
        val testObserver = Completable.complete().applySchedulers(ioScheduler, uiScheduler).test()
        testObserver.assertNotComplete()
        ioScheduler.triggerActions()
        testObserver.assertNotComplete()
        uiScheduler.triggerActions()
        testObserver.assertComplete()
    }

    @Test
    fun maybeApply() {
        val testObserver = Maybe.just(5).applySchedulers(ioScheduler, uiScheduler).test()
        testObserver.assertNoValues()
        ioScheduler.triggerActions()
        testObserver.assertNoValues()
        uiScheduler.triggerActions()
        testObserver.assertValue(5)
        testObserver.assertComplete()
    }
}
