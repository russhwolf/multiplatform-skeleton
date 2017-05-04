package io.intrepid.skotlinton.logging

import android.util.Log
import io.intrepid.skotlinton.BuildConfig
import timber.log.Timber

object TimberConfig {
    fun init(crashReporter: CrashReporter) {
        var tree: Timber.Tree? = null
        if (BuildConfig.LOG_CONSOLE) {
            if (BuildConfig.REPORT_CRASH) {
                tree = object : Timber.DebugTree() {
                    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                        if (priority >= Log.INFO) {
                            crashReporter.log(priority, tag, message)
                        } else {
                            super.log(priority, tag, message, t)
                        }
                    }
                }
            } else {
                tree = Timber.DebugTree()
            }
        } else if (BuildConfig.REPORT_CRASH) {
            tree = object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (priority >= Log.INFO) {
                        crashReporter.log(priority, tag, message)
                    }
                }
            }
        }
        if (tree != null) {
            Timber.plant(tree)
        }
    }
}
