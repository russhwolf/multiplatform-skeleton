package io.intrepid.skotlinton;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.intrepid.skotlinton.base.PresenterConfiguration;
import io.intrepid.skotlinton.logging.CrashlyticsReporter;
import io.intrepid.skotlinton.logging.TimberConfig;
import io.intrepid.skotlinton.rest.RetrofitClient;
import io.intrepid.skotlinton.settings.SharePreferencesManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SkotlintonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

        CrashlyticsReporter.init(this);

        TimberConfig.init(CrashlyticsReporter.getInstance());

        initCalligraphy();
    }

    private void initCalligraphy() {
        CalligraphyConfig config = new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.Roboto_Regular))
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(config);
    }

    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                SharePreferencesManager.getInstance(this),
                RetrofitClient.getApi(),
                CrashlyticsReporter.getInstance()
        );
    }
}
