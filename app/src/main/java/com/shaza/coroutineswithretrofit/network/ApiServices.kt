package com.shaza.coroutineswithretrofit.network

import com.shaza.coroutineswithretrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}