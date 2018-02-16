package io.intrepid.multiskeleton

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.intrepid.multiskeleton.base.PresenterConfiguration
import io.intrepid.multiskeleton.logging.CrashlyticsReporter
import io.intrepid.multiskeleton.logging.TimberConfig
import io.intrepid.multiskeleton.rest.RetrofitClient
import io.intrepid.multiskeleton.settings.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class MultiskeletonApplication : Application() {

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
