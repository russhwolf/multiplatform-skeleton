package io.intrepid.skotlinton.testutils

import org.mockito.Mockito

import io.intrepid.skotlinton.base.PresenterConfiguration
import io.intrepid.skotlinton.logging.CrashReporter
import io.intrepid.skotlinton.rest.RestApi
import io.intrepid.skotlinton.settings.UserSettings
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestPresenterConfiguration(userSettings: UserSettings,
                                 restApi: RestApi,
                                 crashReporter: CrashReporter) : PresenterConfiguration(TestScheduler(), TestScheduler(), userSettings, restApi, crashReporter) {

    override val ioScheduler: TestScheduler
        get() = super.ioScheduler as TestScheduler

    override val uiScheduler: TestScheduler
        get() = super.uiScheduler as TestScheduler

    /**
     * Helper method for triggering pending Rx events
     */
    fun triggerRxSchedulers() {
        ioScheduler.triggerActions()
        uiScheduler.triggerActions()
    }

    companion object {
        fun createTestConfiguration(): TestPresenterConfiguration {
            val mockApi = Mockito.mock(RestApi::class.java)
            val mockSettings = Mockito.mock(UserSettings::class.java)
            val crashReporter = Mockito.mock(CrashReporter::class.java)
            return TestPresenterConfiguration(
                    mockSettings,
                    mockApi,
                    crashReporter
            )
        }
    }
}
