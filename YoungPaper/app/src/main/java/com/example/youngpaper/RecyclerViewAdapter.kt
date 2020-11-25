package com.example.youngpaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {
    private val newsList = arrayListOf<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = newsList.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(newsList[position])
    }

    fun addNews(news: ArrayList<News>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val newsPhoto = itemView.findViewById<ImageView>(R.id.newsPhotoImg)
        private val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        private val newsLead = itemView.findViewById<TextView>(R.id.newsLead)

        fun bind(news: News) {
            newsPhoto?.setImageResource(R.mipmap.ic_launcher)
            newsTitle?.text = news.title
            newsLead?.text = news.lead
        }
    }

}