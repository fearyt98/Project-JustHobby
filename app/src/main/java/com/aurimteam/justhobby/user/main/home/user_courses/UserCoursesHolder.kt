package com.aurimteam.justhobby.user.main.home.user_courses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.response.UserCourseTimetableResponse
import kotlinx.android.synthetic.main.fragment_card_user_course.view.*

class UserCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        title: String,
        description: String,
        address: String,
        tutor: String,
        timeTable: List<UserCourseTimetableResponse>,
        isClicked: Boolean
    ) {
        itemView.cardUserCourseTitle.text = title
        itemView.cardUserCourseDescription.text = description
        itemView.cardUserCourseAddress.text = address
        itemView.cardUserCourseTeacher.text = tutor
        
        itemView.cardUserCourseDay2.visibility = View.GONE
        itemView.cardUserCourseDay3.visibility = View.GONE
        itemView.cardUserCourseDay4.visibility = View.GONE
        itemView.cardUserCourseDay5.visibility = View.GONE
        itemView.cardUserCourseDay6.visibility = View.GONE
        itemView.cardUserCourseDay7.visibility = View.GONE
        itemView.cardUserCourseDay1Str.text = timeTable[0].dayWeek
        itemView.cardUserCourseDay1StartTime.text = timeTable[0].timeStart
        itemView.cardUserCourseDay1EndTime.text = timeTable[0].timeEnd
        if (isClicked) {
            for (item in timeTable) {
                when (timeTable.indexOf(item)) {
                    1 -> {
                        itemView.cardUserCourseDay2Str.text = item.dayWeek
                        itemView.cardUserCourseDay2StartTime.text = item.timeStart
                        itemView.cardUserCourseDay2EndTime.text = item.timeEnd
                        itemView.cardUserCourseDay2.visibility = View.VISIBLE
                    }
                    2 -> {
                        itemView.cardUserCourseDay3Str.text = item.dayWeek
                        itemView.cardUserCourseDay3StartTime.text = item.timeStart
                        itemView.cardUserCourseDay3EndTime.text = item.timeEnd
                        itemView.cardUserCourseDay3.visibility = View.VISIBLE
                    }
                    3 -> {
                        itemView.cardUserCourseDay4Str.text = item.dayWeek
                        itemView.cardUserCourseDay4StartTime.text = item.timeStart
                        itemView.cardUserCourseDay4EndTime.text = item.timeEnd
                        itemView.cardUserCourseDay4.visibility = View.VISIBLE
                    }
                    4 -> {
                        itemView.cardUserCourseDay5Str.text = item.dayWeek
                        itemView.cardUserCourseDay5StartTime.text = item.timeStart
                        itemView.cardUserCourseDay5EndTime.text = item.timeEnd
                        itemView.cardUserCourseDay5.visibility = View.VISIBLE
                    }
                    5 -> {
                        itemView.cardUserCourseDay6Str.text = item.dayWeek
                        itemView.cardUserCourseDay6StartTime.text = item.timeStart
                        itemView.cardUserCourseDay6EndTime.text = item.timeEnd
                        itemView.cardUserCourseDay6.visibility = View.VISIBLE
                    }
                    6 -> {
                        itemView.cardUserCourseDay7Str.text = item.dayWeek
                        itemView.cardUserCourseDay7StartTime.text = item.timeStart
                        itemView.cardUserCourseDay7EndTime.text = item.timeEnd
                        itemView.cardUserCourseDay7.visibility = View.VISIBLE
                    }
                    else -> {
                    }
                }
            }
        }
    }
}