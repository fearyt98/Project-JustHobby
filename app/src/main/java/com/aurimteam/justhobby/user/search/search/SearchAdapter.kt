package com.aurimteam.justhobby.user.search.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CategoryResponse

class SearchAdapter : RecyclerView.Adapter<SearchHolder>() {

    private val categories: MutableList<CategoryResponse> = mutableListOf()

    override fun getItemCount(): Int = categories.size
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(
            categories[position].attributes.slug,
            categories[position].attributes.title
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchHolder =
        SearchHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false)
        )

    fun onDataChange(categories: List<CategoryResponse>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }
}