package io.intrepid.skotlinton.logging

import android.util.Log
import io.intrepid.skotlinton.BuildConfig
import timber.log.Timber

object TimberConfig {
    fun init(crashReporter: CrashReporter) {
        val tree: Timber.Tree? = when {
            BuildConfig.LOG_CONSOLE && BuildConfig.REPORT_CRASH -> object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (priority >= Log.INFO) {
                        crashReporter.log(priority, tag, message)
                    } else {
                        super.log(priority, tag, message, t)
                    }
                }
            }
            BuildConfig.LOG_CONSOLE -> Timber.DebugTree()
            BuildConfig.REPORT_CRASH -> object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (priority >= Log.INFO) {
                        crashReporter.log(priority, tag, message)
                    }
                }
            }
            else -> null
        }
        if (tree != null) {
            Timber.plant(tree)
        }
    }
}
