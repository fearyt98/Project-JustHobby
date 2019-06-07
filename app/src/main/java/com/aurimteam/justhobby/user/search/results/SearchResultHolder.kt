package com.aurimteam.justhobby.user.search.results

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.response.IdentifierResponse
import kotlinx.android.synthetic.main.card_course.view.*

class SearchResultHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(
        id: Long,
        title: String,
        description: String,
        address: String
    ) {
        itemView.cardCourseTitle.text=title
        itemView.cardCourseCompany.text=description
        itemView.cardCourseAddress.text=address
        itemView.cardCourseMetres.text="750 м"
    }
}