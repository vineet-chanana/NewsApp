package com.example.newsapi.articleList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.ArticlesList
import com.example.newsapi.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesListViewModel : ViewModel() {

    private var _artcilesList = MutableLiveData<List<Article>>()
    val articlesList : LiveData<List<Article>>
    get() = _artcilesList


    fun getArticlesList(source:String) {
        val articlesList: Call<ArticlesList> = NewsAPI.retrofit.getArticlesList(source)
        articlesList.enqueue(object :Callback<ArticlesList>{
            override fun onResponse(call: Call<ArticlesList>, response: Response<ArticlesList>) {
                getArticleListValue(response)
            }

            override fun onFailure(call: Call<ArticlesList>, t: Throwable) {
                Log.d("ArticlesListFragment","Failed to fetch articles")
            }

        })

    }

    private fun getArticleListValue(response: Response<ArticlesList>) {
        val responseBody = response.body()
        if(responseBody != null){
            _artcilesList.value = responseBody.articles
        }
    }

}