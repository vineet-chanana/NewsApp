package com.example.newsapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines/sources?apiKey=23a286bb6bfc480bbbcd944581831d2e
//https://newsapi.org/v2/everything?sources=techcrunch&apiKey=23a286bb6bfc480bbbcd944581831d2e
const val BASE_URL = "https://newsapi.org/v2/"

const val API_KEY = "23a286bb6bfc480bbbcd944581831d2e"


interface NewsService {


    @GET("top-headlines/sources?apiKey=$API_KEY&language=en")
    fun getNewsSources() : Call<NewsSource>

    @GET("everything?apiKey=$API_KEY&language=en&pageSize=100")
    fun getArticlesList(@Query("sources") source: String): Call<ArticlesList>
}

object NewsAPI{

    val retrofit: NewsService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)
}
