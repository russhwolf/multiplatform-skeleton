package io.intrepid.multiskeleton.rest

actual interface Call<T> {
    actual fun enqueue(callback: Callback<T>)
    actual fun isExecuted(): Boolean
    actual fun cancel()
    actual fun isCanceled(): Boolean
}

actual interface Callback<T> {
    actual fun onFailure(call: Call<T>, t: Throwable)
    actual fun onResponse(call: Call<T>, response: Response<T>)
}

actual class Response<T>(private val _body: T?, private val _errorBody: ResponseBody?, private val _isSuccessful: Boolean) {
    actual fun isSuccessful() = _isSuccessful
    actual fun body(): T? = _body
    actual fun errorBody(): ResponseBody? = _errorBody
}

actual abstract class ResponseBody(private val _raw: String) {
    override fun toString() = _raw
}
