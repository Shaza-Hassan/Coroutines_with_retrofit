package com.shaza.coroutineswithretrofit

import android.app.Application
import com.shaza.coroutineswithretrofit.repo.PostsRepo

class CoroutinesApplication:Application() {
    val postsRepo by lazy{ PostsRepo()}
}