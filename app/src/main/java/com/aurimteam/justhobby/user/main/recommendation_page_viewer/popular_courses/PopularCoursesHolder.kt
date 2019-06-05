package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.response.IdentifierResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class PopularCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Long,
        title: String,
        description: String,
        address: String,
        company: IdentifierResponse,
        category: IdentifierResponse
    ) {
        itemView.cardCourseTitle.text=title
        itemView.cardCourseDescription.text=description
        itemView.cardCourseAddress.text=address
        itemView.cardCourseMetres.text="750 м"
    }
}