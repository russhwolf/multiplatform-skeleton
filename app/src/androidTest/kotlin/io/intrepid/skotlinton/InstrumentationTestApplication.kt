package io.intrepid.skotlinton

import android.os.AsyncTask
import io.intrepid.skotlinton.base.PresenterConfiguration
import io.intrepid.skotlinton.logging.CrashReporter
import io.intrepid.skotlinton.rest.RestApi
import io.intrepid.skotlinton.rest.RetrofitClient
import io.intrepid.skotlinton.settings.SharedPreferencesManager
import io.intrepid.skotlinton.settings.UserSettings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mockito.Mockito

class InstrumentationTestApplication : SkotlintonApplication() {
    override fun getPresenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                Schedulers.from(AsyncTask.SERIAL_EXECUTOR),
                AndroidSchedulers.mainThread(),
                userSettingsOverride ?: SharedPreferencesManager.getInstance(this),
                restApiOverride ?: RetrofitClient.restApi,
                Mockito.mock(CrashReporter::class.java)
        )
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
