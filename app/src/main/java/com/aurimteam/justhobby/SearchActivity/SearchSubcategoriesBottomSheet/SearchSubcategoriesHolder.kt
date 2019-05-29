package com.aurimteam.justhobby.SearchActivity.SearchSubcategoriesBottomSheet

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_subcategories.view.*

class SearchSubcategoriesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Int,
        slug: String,
        title: String
    ) {
        itemView.subcategoryTitle.text = title
    }
}