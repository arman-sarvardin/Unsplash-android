package com.example.unsplash.network

import com.example.unsplash.R
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkProvider {
    val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .addInterceptor(
            Interceptor {
                val originalRequest = it.request()
                val mofifiedRequest = originalRequest.newBuilder()
                    .addHeader("Accept-Version", "v1")
                    .addHeader("Authorization", "Client-ID ${R.string.access_key}")
                    .build()
                return@Interceptor it.proceed(mofifiedRequest)
            }
        )
        .build()

      val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}