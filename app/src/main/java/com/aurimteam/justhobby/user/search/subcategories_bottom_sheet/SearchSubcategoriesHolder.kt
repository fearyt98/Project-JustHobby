package com.aurimteam.justhobby.user.search.subcategories_bottom_sheet

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.card_subcategories.view.*

class SearchSubcategoriesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Int,
        slug: String,
        title: String
    ) {
        itemView.subcategoryTitle.text = title
    }
}