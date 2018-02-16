package io.intrepid.multiskeleton.testutils

import io.intrepid.multiskeleton.base.BaseContract
import io.intrepid.multiskeleton.base.BasePresenter
import io.intrepid.multiskeleton.logging.CrashReporter
import io.intrepid.multiskeleton.rest.RestApi
import io.intrepid.multiskeleton.settings.UserSettings
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class BasePresenterTest<P : BasePresenter<out BaseContract.View>> {
    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    protected lateinit var presenter: P
    protected lateinit var testConfiguration: TestPresenterConfiguration
    protected lateinit var ioScheduler: TestScheduler
    protected lateinit var uiScheduler: TestScheduler
    protected lateinit var mockRestApi: RestApi
    protected lateinit var mockUserSettings: UserSettings
    protected lateinit var mockCrashReporter: CrashReporter

    @Before
    fun baseSetup() {
        testConfiguration = TestPresenterConfiguration.createTestConfiguration()
        ioScheduler = testConfiguration.ioScheduler
        uiScheduler = testConfiguration.uiScheduler
        mockRestApi = testConfiguration.restApi
        mockUserSettings = testConfiguration.userSettings
        mockCrashReporter = testConfiguration.crashReporter
    }
}
