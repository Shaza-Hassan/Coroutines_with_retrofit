package com.shaza.coroutineswithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shaza.coroutineswithretrofit.viewModel.MainActivityViewModel
import com.shaza.coroutineswithretrofit.viewModel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel:MainActivityViewModel by lazy {
        ViewModelProvider(this,MainActivityViewModelFactory(application as CoroutinesApplication))
            .get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        initObservers()
    }

    private fun getData(){
        viewModel.getPosts()
    }

    private fun initObservers(){
        viewModel.postsListLiveData.observe(this, Observer {
            val post = it[0]
            text_view.text = post.title + "\n\n" + post.body
            Log.v("posts count",it.size.toString())
        })

        viewModel.errorLiveData.observe(this, Observer {
            text_view.text = it
        })

        viewModel.loadingLiveData.observe(this, Observer {
            if (it){
                progressBar.visibility = VISIBLE
                text_view.visibility = GONE
            }else{
                progressBar.visibility = GONE
                text_view.visibility = VISIBLE
            }
        })
    }
}