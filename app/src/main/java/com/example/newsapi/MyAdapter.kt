package com.example.newsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val listener: onSourceClickListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var sourcesList: List<Source>
    fun setData(data: List<Source>){
        sourcesList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //convert xml to java object
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.source_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.sourceName.text = sourcesList[position].name
        holder.sourceDescription.text = sourcesList[position].description
    }

    override fun getItemCount(): Int {
        return sourcesList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var sourceName: TextView = itemView.findViewById<TextView>(R.id.source_name)
        val sourceDescription:TextView = itemView.findViewById(R.id.sourceDescription)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onSourceClick(position)
        }

    }

    interface onSourceClickListener{
        fun onSourceClick(position: Int)
    }
}