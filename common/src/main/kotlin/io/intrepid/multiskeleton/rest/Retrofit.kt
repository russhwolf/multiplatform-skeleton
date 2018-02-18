package io.intrepid.multiskeleton.rest

expect interface Call<T> {
    fun enqueue(callback: Callback<T>)
    fun isExecuted(): Boolean
    fun cancel()
    fun isCanceled(): Boolean
}

expect interface Callback<T> {
    fun onFailure(call: Call<T>, t: Throwable)
    fun onResponse(call: Call<T>, response: Response<T>)
}

expect class Response<T> {
    fun isSuccessful(): Boolean
    fun body(): T?
    fun errorBody(): ResponseBody?
}

expect abstract class ResponseBody
