package io.intrepid.skotlinton.testutils

import org.junit.Before
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import io.intrepid.skotlinton.base.BasePresenter
import io.intrepid.skotlinton.logging.CrashReporter
import io.intrepid.skotlinton.rest.RestApi
import io.intrepid.skotlinton.settings.UserSettings
import io.reactivex.schedulers.TestScheduler

open class BasePresenterTest<P : BasePresenter<*>> {
    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

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
