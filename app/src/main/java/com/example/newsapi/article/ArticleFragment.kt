package com.example.newsapi.article

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.newsapi.R


class ArticleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_article, container, false)

        setArticleWebView(view)

        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setArticleWebView(view: View) {
        val articleUrl = arguments?.getString("articleUrl")

        val articleWebView : WebView = view.findViewById(R.id.articleWebView)
        val articleProgressBar: ProgressBar = view.findViewById(R.id.articleProgressBar)
        if(articleUrl != null){
            //if page has javascript allow it to enable
            articleWebView.settings.javaScriptEnabled = true
            articleWebView.webViewClient = object: WebViewClient(){
                //on page load
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    //hide the progress bar
                    articleProgressBar.visibility = View.GONE
                    //show web view
                    articleWebView.visibility = View.VISIBLE
                }
            }
            articleWebView.loadUrl(articleUrl)
        }
    }


    companion object{
        const val TAG = "articleFragmentTag"
    }

}