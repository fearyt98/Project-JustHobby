package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.SubcategoryAttrResponse
import com.aurimteam.justhobby.response.SubcategoryResponse

class SearchSubcategoriesAdapter : RecyclerView.Adapter<SearchSubcategoriesHolder>() {

    private val subcategories: MutableList<SubcategoryResponse> = mutableListOf()

    override fun getItemCount(): Int = subcategories.size
    override fun onBindViewHolder(holder: SearchSubcategoriesHolder, position: Int) {
        holder.bind(
            subcategories[position].attributes.title,
            subcategories[position].attributes.slug,
            subcategories[position].attributes.created_at,
            subcategories[position].attributes.updated_at
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchSubcategoriesHolder = SearchSubcategoriesHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_subcategories, parent, false)
    )

    fun onDataChange(subcategories: List<SubcategoryResponse>) {
        this.subcategories.clear()
        this.subcategories.addAll(subcategories)
        notifyDataSetChanged()
    }
}