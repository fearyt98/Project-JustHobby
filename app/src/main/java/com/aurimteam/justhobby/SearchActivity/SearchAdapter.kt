package com.aurimteam.justhobby.SearchActivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class SearchAdapter : RecyclerView.Adapter<SearchHolder>() {

    private val categories: MutableList<String> = mutableListOf()

    override fun getItemCount(): Int = categories.size
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchHolder = SearchHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_search_category, parent, false)
    )

    fun onDataChange(categories: List<String>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }
}