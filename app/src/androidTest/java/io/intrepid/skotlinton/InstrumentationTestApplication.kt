package io.intrepid.skotlinton

import android.os.AsyncTask

import org.mockito.Mockito

import io.intrepid.skotlinton.base.PresenterConfiguration
import io.intrepid.skotlinton.logging.CrashReporter
import io.intrepid.skotlinton.rest.RestApi
import io.intrepid.skotlinton.rest.RetrofitClient
import io.intrepid.skotlinton.settings.SharePreferencesManager
import io.intrepid.skotlinton.settings.UserSettings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InstrumentationTestApplication : SkotlintonApplication() {

    override val presenterConfiguration: PresenterConfiguration by lazy {
        PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR),
                AndroidSchedulers.mainThread(),
                userSettingsOverride ?: SharePreferencesManager.getInstance(this),
                restApiOverride ?: RetrofitClient.api,
                Mockito.mock(CrashReporter::class.java))
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
