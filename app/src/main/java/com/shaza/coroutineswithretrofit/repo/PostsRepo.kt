package com.shaza.coroutineswithretrofit.repo

import com.shaza.coroutineswithretrofit.model.Post
import com.shaza.coroutineswithretrofit.network.NetworkFactory
import retrofit2.Response

class PostsRepo {
    val service = NetworkFactory.makeRetrofitService()

    suspend fun getAllPosts():Response<List<Post>>{
        return  service.getPosts()
    }
}