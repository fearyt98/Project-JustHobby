package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.response.CourseAttrResponse
import com.aurimteam.justhobby.response.IdentifierResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class CourseHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        type: String,
        id: Long,
        attributes: CourseAttrResponse
    ) {
        itemView.cardCourseTitle.text = attributes.title
        itemView.cardCourseDescription.text = attributes.description
        itemView.cardCourseAddress.text = attributes.address
        itemView.cardCourseMetres.text = "750 м"
    }
}