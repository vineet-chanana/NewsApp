package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapi.article.ArticleFragment
import com.example.newsapi.articleList.ArticleListFragment
import com.example.newsapi.articleList.ArticleWebViewCommunicator
import com.example.newsapi.source.ArticleListCommunicator
import com.example.newsapi.source.SourcesFragment

class MainActivity : AppCompatActivity(), ArticleListCommunicator, ArticleWebViewCommunicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                SourcesFragment()
            ).commit()
        }

    }

    override fun openArticleList(source: String) {
        val bundle = Bundle()
        bundle.putString("source",source)
        val articlesListFragment = ArticleListFragment()
        articlesListFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,articlesListFragment).addToBackStack(
            ArticleListFragment.TAG).commit()
    }

    override fun openArticleWebView(url:String) {
        val bundle = Bundle()
        bundle.putString("articleUrl",url)
        val articleFragment = ArticleFragment()
        articleFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,articleFragment).addToBackStack(
            ArticleFragment.TAG).commit()
    }

}