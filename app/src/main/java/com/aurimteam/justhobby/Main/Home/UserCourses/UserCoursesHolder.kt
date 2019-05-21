package com.aurimteam.justhobby.Main.Home.UserCourses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.Response.UserCourseTimetableResponse
import kotlinx.android.synthetic.main.fragment_card_user_course.view.*

class UserCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        title: String,
        description: String,
        address: String,
        tutor: String
    ) {
        itemView.userCoursesTitle.text = title
        itemView.userCoursesDescription.text = description
        itemView.userCoursesAddress.text = address
        itemView.userCoursesTutor.text = tutor
    }
}