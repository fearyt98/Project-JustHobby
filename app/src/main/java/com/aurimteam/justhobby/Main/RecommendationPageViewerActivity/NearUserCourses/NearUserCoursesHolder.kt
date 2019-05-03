package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.NearUserCourses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.Response.IdentifierResponse
import kotlinx.android.synthetic.main.activity_card_course.view.*

class NearUserCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Long,
        title: String,
        description: String,
        address: String,
        company: IdentifierResponse,
        category: IdentifierResponse
    ) {
        itemView.titleCourse.text = title
        itemView.descriptionCourse.text = description
        itemView.addressCourse.text = address
        itemView.metresCourse.text = "750 м"
    }
}