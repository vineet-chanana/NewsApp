package com.example.newsapi.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.NewsAPI
import com.example.newsapi.NewsSource
import com.example.newsapi.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourcesFragmentViewModel : ViewModel(){
    private var _sourcesList = MutableLiveData<List<Source>>()
    val sourcesList : LiveData<List<Source>>
        get() = _sourcesList

    init {
        getSourcesList()
    }

    private fun getSourcesList() {
        val newsSource: Call<NewsSource> = NewsAPI.retrofit.getNewsSources()
        newsSource.enqueue(object: Callback<NewsSource> {
            override fun onResponse(call: Call<NewsSource>, response: Response<NewsSource>) {
                getSourcesListValue(response)
            }

            override fun onFailure(call: Call<NewsSource>, t: Throwable) {
                Log.d("Main","error in fetching news sources")
            }
        })
    }

    private fun getSourcesListValue(response: Response<NewsSource>) {
        val newsBody = response.body()
        if(newsBody != null) {
            _sourcesList.value = newsBody.sources
        }
    }


}

