package com.example.statehandlinginandroid.retrofitsdk

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class InterceptorHTTPClientCreator {
    companion object {

        private var defaultHttpClient: OkHttpClient? = null

        @JvmStatic
        fun createInterceptorHTTPClient() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            defaultHttpClient = OkHttpClient.Builder().addInterceptor {
                    val request =
                        it.request()
                            .newBuilder()
                            .header("Content-Type", "application/json")
                            .build()

                    it.proceed(request)
                }
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()
        }


        @JvmStatic
        val isHttpClientNull
            get() = defaultHttpClient == null

        @JvmStatic
        internal val okHttpClient
            get() = if (defaultHttpClient != null) {
                defaultHttpClient
            } else null

        @JvmStatic
        fun clearOkHttpClient() {
            defaultHttpClient = null
        }
    }
}