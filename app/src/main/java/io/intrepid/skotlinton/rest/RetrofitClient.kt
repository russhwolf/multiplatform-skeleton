package io.intrepid.skotlinton.rest

import android.support.annotation.VisibleForTesting

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.util.concurrent.TimeUnit

import io.intrepid.skotlinton.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitClient {

    // TODO: change this to a real endpoint
    private val BASE_URL = "https://api.ipify.org/"
    private val CONNECTION_TIMEOUT = 30

    private var instance: RestApi? = null

    val api: RestApi
        get() {
            if (instance == null) {
                instance = createRestApi(BASE_URL)
            }
            return instance
        }

    @VisibleForTesting
    internal fun getTestApi(baseUrl: String): RestApi {
        return createRestApi(baseUrl)
    }

    private fun createRestApi(baseUrl: String): RestApi {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.LOG_CONSOLE) {
            builder.addInterceptor(HttpLoggingInterceptor { message -> Timber.v(message) }.setLevel(
                    HttpLoggingInterceptor.Level.BODY))
        }
        val httpClient = builder
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestApi::class.java)
    }
}
