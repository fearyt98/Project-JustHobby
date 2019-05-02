package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.PopularCourses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.Response.IdentifierResponse
import kotlinx.android.synthetic.main.activity_card_course.view.*

class PopularCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Long,
        title: String,
        description: String,
        address: String,
        company: IdentifierResponse,
        category: IdentifierResponse
    ) {
        itemView.titlePopularCourse.text=title
        itemView.descriptionPopularCourse.text=description
        itemView.addressPopularCourse.text=address
        itemView.metresPopularCourse.text="750 м"
    }
}