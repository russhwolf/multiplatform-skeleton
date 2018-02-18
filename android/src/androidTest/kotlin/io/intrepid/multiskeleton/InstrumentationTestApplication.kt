package io.intrepid.multiskeleton

import io.intrepid.multiskeleton.base.PresenterConfiguration
import io.intrepid.multiskeleton.logging.CrashReporter
import io.intrepid.multiskeleton.logging.TimberLogger
import io.intrepid.multiskeleton.rest.RestApi
import io.intrepid.multiskeleton.rest.RetrofitClient
import io.intrepid.multiskeleton.settings.SharedPreferencesManager
import io.intrepid.multiskeleton.settings.UserSettings
import org.mockito.Mockito

class InstrumentationTestApplication : MultiskeletonApplication() {
    override fun getPresenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                userSettingsOverride ?: SharedPreferencesManager.getInstance(this),
                restApiOverride ?: RetrofitClient.restApi,
                TimberLogger,
                Mockito.mock(CrashReporter::class.java)
        )
    }

    override fun setupLeakCanary() {
        // noop, we don't want LeakCanary in UI tests
    }

    companion object {
        private var restApiOverride: RestApi? = null
        private var userSettingsOverride: UserSettings? = null

        fun overrideRestApi(restApi: RestApi) {
            restApiOverride = restApi
        }

        fun clearRestApiOverride() {
            restApiOverride = null
        }

        fun overrideUserSettings(userSettings: UserSettings) {
            userSettingsOverride = userSettings
        }

        fun clearUserSettingsOverride() {
            userSettingsOverride = null
        }
    }
}
