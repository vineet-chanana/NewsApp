package com.example.newsapi

import android.os.Bundle
import android.view.*
//import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ArticleListFragment : Fragment(), ArticlesListAdapter.onArticleClickListener{

    private lateinit var viewModel: ArticlesListViewModel
    private lateinit var communicator:Communicator2
    private lateinit var adapter: ArticlesListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_article_list, container, false)


        viewModel = ViewModelProvider(this).get(ArticlesListViewModel::class.java)

        val newsSource: String? = arguments?.getString("source")


        val articlesListRecyclerView:RecyclerView = view.findViewById(R.id.articlesListRecyclerView)
        articlesListRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ArticlesListAdapter(this)

        passSourceName(newsSource)

        viewModel.articlesList.observe(viewLifecycleOwner) { newArticlesList ->
            adapter.setData(newArticlesList)
            articlesListRecyclerView.adapter = adapter
        }


        communicator = activity as Communicator2

        setHasOptionsMenu(true);
        return view
    }

    private fun passSourceName(newsSource: String?) {
        if (newsSource != null) {
            viewModel.getArticlesList(newsSource)
        }
    }

    companion object{
        const val TAG = "articleListFragmentTag"
    }

    override fun onArticleClick(position: Int) {
        //tell the activity to open Article Web View Fragment
        communicator.openArticleWebView(viewModel.articlesList.value?.get(position)?.url.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        //menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu?.findItem(R.id.article_search);
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

    }
}