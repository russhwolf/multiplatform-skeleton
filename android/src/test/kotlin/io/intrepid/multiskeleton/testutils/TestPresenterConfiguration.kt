package io.intrepid.multiskeleton.testutils

import io.intrepid.multiskeleton.base.PresenterConfiguration
import io.intrepid.multiskeleton.logging.CrashReporter
import io.intrepid.multiskeleton.rest.RestApi
import io.intrepid.multiskeleton.settings.UserSettings
import org.mockito.Mockito

class TestPresenterConfiguration private constructor(userSettings: UserSettings, restApi: RestApi, crashReporter: CrashReporter)
    : PresenterConfiguration(userSettings, restApi, crashReporter) {

    companion object {
        fun createTestConfiguration(): TestPresenterConfiguration {
            val userSettings = Mockito.mock(UserSettings::class.java)
            val restApi = Mockito.mock(RestApi::class.java)
            val crashReporter = Mockito.mock(CrashReporter::class.java)
            return TestPresenterConfiguration(userSettings, restApi, crashReporter)
        }
    }
}
