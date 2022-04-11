package com.example.newsapi

import android.os.Bundle
import android.os.RecoverySystem
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SourcesFragment : Fragment(),MyAdapter.onSourceClickListener {

    private lateinit var viewModel: SourcesFragmentViewModel
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sources, container, false)

        viewModel = ViewModelProvider(this).get(SourcesFragmentViewModel::class.java)

        val sourcesListRecyclerView: RecyclerView = view.findViewById(R.id.sourcesListRecyclerView)
        sourcesListRecyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = MyAdapter(this)



        viewModel.sourcesList.observe(viewLifecycleOwner) { newSourcesList ->
            adapter.setData(newSourcesList)
            sourcesListRecyclerView.adapter = adapter
        }

        communicator = activity as Communicator

        return view
    }

    override fun onSourceClick(position: Int) {
        //tell the activity to open article List Fragment
        communicator.openArticleList(viewModel.sourcesList.value?.get(position)?.id.toString())
    }


}

//ek hi viewmodel use krege to data preserve rhega and we dont want that bcoz next time agar hum kisi or pe click krege to phle prev wala article dikhega