package io.intrepid.multiskeleton.logging

interface Logger {
    fun v(message: String)
    fun v(t: Throwable, message: String)
    fun v(t: Throwable)

    fun d(message: String)
    fun d(t: Throwable, message: String)
    fun d(t: Throwable)

    fun i(message: String)
    fun i(t: Throwable, message: String)
    fun i(t: Throwable)

    fun w(message: String)
    fun w(t: Throwable, message: String)
    fun w(t: Throwable)

    fun e(message: String)
    fun e(t: Throwable, message: String)
    fun e(t: Throwable)

    fun wtf(message: String)
    fun wtf(t: Throwable, message: String)
    fun wtf(t: Throwable)
}
