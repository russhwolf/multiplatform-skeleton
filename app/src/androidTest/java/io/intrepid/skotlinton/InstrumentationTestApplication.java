package io.intrepid.skotlinton;

import android.os.AsyncTask;

import org.mockito.Mockito;

import io.intrepid.skotlinton.base.PresenterConfiguration;
import io.intrepid.skotlinton.logging.CrashReporter;
import io.intrepid.skotlinton.rest.RestApi;
import io.intrepid.skotlinton.rest.RetrofitClient;
import io.intrepid.skotlinton.settings.SharePreferencesManager;
import io.intrepid.skotlinton.settings.UserSettings;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InstrumentationTestApplication extends SkotlintonApplication {
    private static RestApi restApiOverride = null;
    private static UserSettings userSettingsOverride = null;

    @Override
    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR),
                AndroidSchedulers.mainThread(),
                userSettingsOverride != null ? userSettingsOverride : SharePreferencesManager.getInstance(this),
                restApiOverride != null ? restApiOverride : RetrofitClient.getApi(),
                Mockito.mock(CrashReporter.class));
    }

    public static void overrideRestApi(RestApi restApi) {
        restApiOverride = restApi;
    }

    public static void clearRestApiOverride() {
        restApiOverride = null;
    }

    public static void overrideUserSettings(UserSettings userSettings) {
        userSettingsOverride = userSettings;
    }

    public static void clearUserSettingsOverride() {
        userSettingsOverride = null;
    }
}
