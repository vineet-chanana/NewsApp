package com.example.newsapi

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsapi.article.Article
import java.util.*

class ArticlesListAdapter(private val listener: onArticleClickListener) : RecyclerView.Adapter<ArticlesListAdapter.ArticleViewHolder>(),Filterable{

    lateinit var _articleList: List<Article>
    lateinit var articlesList : List<Article>
    lateinit var filteredArticlesList: List<Article>
    fun setData(data: List<Article>){
        articlesList = data
        _articleList = articlesList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_view,parent,false)

        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        //holder.articleImage = articlesList[position].urlToImage
        holder.articleTitle.text = articlesList[position].title
        holder.articleDescription.text = articlesList[position].description
        holder.articleAuthor.text = articlesList[position].author
        val temp = articlesList[position].publishedAt

        val radius = 30 // corner radius, higher value = more rounded

        val margin = 10
        val media = articlesList[position].urlToImage
        if (!media.isNullOrEmpty()) {

            Glide.with(holder.itemView.context)
                .load(media)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.not_found_image_3)
                .fallback(ColorDrawable(Color.GRAY))
                .into(holder.articleImage)

        } else {
            holder.articleImage.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int{
        return articlesList.size
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val articleImage:ImageView = itemView.findViewById(R.id.articleImage)
        val articleTitle:TextView = itemView.findViewById(R.id.articleTitle)
        val articleDescription:TextView = itemView.findViewById(R.id.articleDescription)
        val articleAuthor:TextView = itemView.findViewById(R.id.articleAuthor)
        //val articlePublished:TextView = itemView.findViewById(R.id.articlePublishedAt)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onArticleClick(position)
        }
    }

    interface onArticleClickListener{
        fun onArticleClick(position: Int)
    }

    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint.toString()
                if (searchText.isEmpty()) {
                    articlesList = _articleList
                } else {
                    val resultList = ArrayList<Article>()
                    for (row in _articleList) {
                        if (row.title.lowercase(Locale.getDefault()).contains(searchText)) {
                            resultList.add(row)
                        }
                    }
                    articlesList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = articlesList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredArticlesList = results?.values as List<Article>
                notifyDataSetChanged()
            }

        }
    }
}