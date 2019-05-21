package com.aurimteam.justhobby.Main.Home.UserCourses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.UserCourseTimetableResponse

class UserCoursesTimetableAdapter : RecyclerView.Adapter<UserCourseTimetableHolder>() {

    private val userCoursesTimetableList: MutableList<List<UserCourseTimetableResponse>> = mutableListOf()

    override fun getItemCount(): Int = userCoursesTimetableList.size
    override fun onBindViewHolder(holder: UserCourseTimetableHolder, position: Int) {
        for (item in userCoursesTimetableList[position])
            holder.bind(
                item.dayWeek,
                item.timeStart,
                item.timeEnd
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserCourseTimetableHolder =
        UserCourseTimetableHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_user_course_card_day, parent, false)
        )

    fun onDataChange(userCoursesTimetableList: List<List<UserCourseTimetableResponse>>) {
        this.userCoursesTimetableList.clear()
        this.userCoursesTimetableList.addAll(userCoursesTimetableList)
        notifyDataSetChanged()
    }
}
