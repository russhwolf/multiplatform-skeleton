package io.intrepid.skotlinton

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.intrepid.skotlinton.base.PresenterConfiguration
import io.intrepid.skotlinton.logging.CrashlyticsReporter
import io.intrepid.skotlinton.logging.TimberConfig
import io.intrepid.skotlinton.rest.RetrofitClient
import io.intrepid.skotlinton.settings.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class SkotlintonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLeakCanary()

        CrashlyticsReporter.init(this)

        TimberConfig.init(CrashlyticsReporter)
    }

    protected open fun setupLeakCanary() {
        LeakCanary.install(this)
    }

    open fun getPresenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                SharedPreferencesManager.getInstance(this),
                RetrofitClient.restApi,
                CrashlyticsReporter
        )
    }
}
