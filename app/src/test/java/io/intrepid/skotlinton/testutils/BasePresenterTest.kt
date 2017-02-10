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
    var mockitoRule = MockitoJUnit.rule()

    protected var presenter: P? = null
    protected var testConfiguration: TestPresenterConfiguration
    protected var ioScheduler: TestScheduler
    protected var uiScheduler: TestScheduler
    protected var mockRestApi: RestApi
    protected var mockUserSettings: UserSettings
    protected var mockCrashReporter: CrashReporter

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
