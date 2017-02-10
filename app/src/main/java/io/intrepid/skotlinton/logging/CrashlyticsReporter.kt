package io.intrepid.skotlinton.logging

import android.content.Context

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore

import io.fabric.sdk.android.Fabric
import io.intrepid.skotlinton.BuildConfig

class CrashlyticsReporter private constructor() : CrashReporter {

    override fun logException(throwable: Throwable) {
        Crashlytics.logException(throwable)
    }

    override fun log(priority: Int, tag: String, message: String) {
        Crashlytics.log(priority, tag, message)
    }

    companion object {

        private var instance: CrashlyticsReporter? = null

        fun init(context: Context) {
            val core = CrashlyticsCore.Builder()
                    .disabled(!BuildConfig.REPORT_CRASH)
                    .build()
            val crashlytics = Crashlytics.Builder()
                    .core(core)
                    .build()
            Fabric.with(context, crashlytics)
            instance = CrashlyticsReporter()
        }

        fun getInstance(): CrashReporter {
            return instance
        }
    }
}
