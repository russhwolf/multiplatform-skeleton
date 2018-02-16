package io.intrepid.multiskeleton.logging

interface CrashReporter {

    fun log(priority: Int, tag: String?, message: String)

    fun logException(throwable: Throwable)
}
