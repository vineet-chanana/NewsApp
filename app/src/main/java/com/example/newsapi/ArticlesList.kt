package com.example.newsapi

import com.example.newsapi.article.Article

data class ArticlesList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)