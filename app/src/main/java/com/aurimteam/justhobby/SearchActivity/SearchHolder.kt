package com.aurimteam.justhobby.SearchActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_search_category.view.*

class SearchHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(
        categoryTitle: String
    )
    {
        itemView.searchCardTitle.text = categoryTitle
    }
}