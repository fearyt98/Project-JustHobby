package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.SubcategoriesResponse

class SearchSubcategoriesAdapter : RecyclerView.Adapter<SearchSubcategoriesHolder>() {

    private val subcategories: MutableList<SubcategoriesResponse> = mutableListOf()

    override fun getItemCount(): Int = subcategories.size
    override fun onBindViewHolder(holder: SearchSubcategoriesHolder, position: Int) {
        holder.bind(
            subcategories[position].id,
            subcategories[position].slug,
            subcategories[position].title
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchSubcategoriesHolder = SearchSubcategoriesHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_subcategories, parent, false)
    )

    fun onDataChange(subcategories: List<SubcategoriesResponse>) {
        this.subcategories.clear()
        this.subcategories.addAll(subcategories)
        notifyDataSetChanged()
    }
}