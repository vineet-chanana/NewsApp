package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),Communicator,Communicator2 {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,SourcesFragment()).commit()
        }

    }

    override fun openArticleList(source: String) {
        val bundle = Bundle()
        bundle.putString("source",source)
        val articlesListFragment = ArticleListFragment()
        articlesListFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,articlesListFragment).addToBackStack(ArticleListFragment.TAG).commit()
    }

    override fun openArticleWebView(url:String) {
        val bundle = Bundle()
        bundle.putString("articleUrl",url)
        val articleFragment = ArticleFragment()
        articleFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,articleFragment).addToBackStack(ArticleFragment.TAG).commit()
    }

}