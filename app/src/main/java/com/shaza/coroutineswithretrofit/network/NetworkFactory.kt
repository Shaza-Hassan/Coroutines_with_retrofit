package com.shaza.coroutineswithretrofit.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkFactory {

    const val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun makeRetrofitService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ApiServices::class.java)
    }
}