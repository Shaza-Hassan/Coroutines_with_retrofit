package com.shaza.coroutineswithretrofit.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shaza.coroutineswithretrofit.CoroutinesApplication
import com.shaza.coroutineswithretrofit.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val application: CoroutinesApplication):AndroidViewModel(application) {
    val postsListLiveData = MutableLiveData<List<Post>>()
    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()


    fun getPosts(){
        loadingLiveData.value = true
        CoroutineScope(IO).launch {
            val response = application.postsRepo.getAllPosts()
            if (response.isSuccessful){
                loadingLiveData.postValue(false)
                withContext(Main){
                    postsListLiveData.value = response.body()
                }
            }else{
                loadingLiveData.postValue(false)
                withContext(Main){
                    errorLiveData.value = response.message()
                }
            }
        }
    }
}

class MainActivityViewModelFactory(private val application: CoroutinesApplication) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}