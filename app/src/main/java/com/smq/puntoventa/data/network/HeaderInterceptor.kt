package com.smq.puntoventa.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "Content-Type", "application/x-www-form-urlencoded"
            )
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}