package com.aurimteam.justhobby.Main.Home.UserCourses

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_user_course_card_day.view.*

class UserCourseTimetableHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        dayWeek: String,
        timeStart: String,
        timeEnd: String
    ) {
        itemView.cardDayStartTime.text = timeStart
        itemView.cardDayEndTime.text = timeEnd
        itemView.cardDayTitle.text = dayWeek
    }

}