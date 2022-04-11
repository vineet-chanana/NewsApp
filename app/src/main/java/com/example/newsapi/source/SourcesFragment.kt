package com.example.newsapi.source

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.R

class SourcesFragment : Fragment(), SourceAdapter.onSourceClickListener {

    private lateinit var viewModel: SourcesFragmentViewModel
    private lateinit var communicator: ArticleListCommunicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sources, container, false)

        viewModel = ViewModelProvider(this).get(SourcesFragmentViewModel::class.java)


        val sourcesListRecyclerView: RecyclerView = view.findViewById(R.id.sourcesListRecyclerView)
        sourcesListRecyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = SourceAdapter(this)


        viewModel.sourcesList.observe(viewLifecycleOwner) { newSourcesList ->
            adapter.setData(newSourcesList)
            sourcesListRecyclerView.adapter = adapter
        }

        communicator = activity as ArticleListCommunicator

        return view
    }


    override fun onSourceClick(position: Int) {
        //tell the activity to open article List Fragment
        communicator.openArticleList(viewModel.sourcesList.value?.get(position)?.id.toString())
    }


}