package io.intrepid.multiskeleton.logging

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric
import io.intrepid.multiskeleton.BuildConfig

object CrashlyticsReporter : CrashReporter {

    fun init(context: Context) {
        val core = CrashlyticsCore.Builder().disabled(!BuildConfig.REPORT_CRASH).build()
        val crashlytics = Crashlytics.Builder().core(core).build()
        Fabric.with(context, crashlytics)
    }

    override fun log(priority: Int, tag: String?, message: String) {
        Crashlytics.log(priority, tag, message)
    }

    override fun logException(throwable: Throwable) {
        Crashlytics.logException(throwable)
    }
}
