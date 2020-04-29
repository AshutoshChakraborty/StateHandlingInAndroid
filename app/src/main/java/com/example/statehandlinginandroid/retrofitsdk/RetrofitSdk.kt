package com.example.statehandlinginandroid.retrofitsdk

import android.content.Context
import com.example.statehandlinginandroid.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSdk(private val retrofit: Retrofit) {
    private  var service: Service? = null

    init {
        createService()
    }

    /**
     * Builder for [RetrofitSdk]
     */
    class Builder {

        /**
         * Create the [RetrofitSdk] to be used.
         *
         * @return [RetrofitSdk]
         */

        fun build(context: Context): RetrofitSdk {
            val retrofit: Retrofit
            val baseUrl: String = context.resources.getString(R.string.base_url)

            val okHttpClient = InterceptorHTTPClientCreator.okHttpClient
            return if (okHttpClient != null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .build()

                RetrofitSdk(retrofit)
            } else {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
                RetrofitSdk(retrofit)
            }
        }
    }

    private fun createService() {
        service = retrofit.create(Service::class.java)
    }

    fun getService(): Service? {
        return service
    }
}