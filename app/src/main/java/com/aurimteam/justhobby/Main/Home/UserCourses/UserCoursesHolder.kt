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
        tutor: String,
        timeTable: List<UserCourseTimetableResponse>,
        isClicked: Boolean
    ) {
        itemView.userCoursesTitle.text = title
        itemView.userCoursesDescription.text = description
        itemView.userCoursesAddress.text = address
        itemView.userCoursesTutor.text = tutor
        if(isClicked) {
            itemView.mondayWeek.visibility = View.GONE
            itemView.tuesdayWeek.visibility = View.GONE
            itemView.wednesdayWeek.visibility = View.GONE
            itemView.thursdayWeek.visibility = View.GONE
            itemView.fridayWeek.visibility = View.GONE
            itemView.saturdayWeek.visibility = View.GONE
            itemView.sundayWeek.visibility = View.GONE
            for (item in timeTable) {
                when (item.dayWeek) {
                    "ПН" -> {
                        itemView.mondayStartTime.text = item.timeStart
                        itemView.mondayEndTime.text = item.timeEnd
                        itemView.mondayWeek.visibility = View.VISIBLE
                    }
                    "ВТ" -> {
                        itemView.tuesdayStartTime.text = item.timeStart
                        itemView.tuesdayEndTime.text = item.timeEnd
                        itemView.tuesdayWeek.visibility = View.VISIBLE
                    }
                    "СР" -> {
                        itemView.wednesdayStartTime.text = item.timeStart
                        itemView.wednesdayEndTime.text = item.timeEnd
                        itemView.wednesdayWeek.visibility = View.VISIBLE
                    }
                    "ЧТ" -> {
                        itemView.thursdayStartTime.text = item.timeStart
                        itemView.thursdayEndTime.text = item.timeEnd
                        itemView.thursdayWeek.visibility = View.VISIBLE
                    }
                    "ПТ" -> {
                        itemView.fridayStartTime.text = item.timeStart
                        itemView.fridayEndTime.text = item.timeEnd
                        itemView.fridayWeek.visibility = View.VISIBLE
                    }
                    "СБ" -> {
                        itemView.saturdayStartTime.text = item.timeStart
                        itemView.saturdayEndTime.text = item.timeEnd
                        itemView.saturdayWeek.visibility = View.VISIBLE
                    }
                    "ВС" -> {
                        itemView.sundayStartTime.text = item.timeStart
                        itemView.sundayEndTime.text = item.timeEnd
                        itemView.sundayWeek.visibility = View.VISIBLE
                    }
                }
            }
        } else {
            itemView.mondayWeek.visibility = View.GONE
            itemView.tuesdayWeek.visibility = View.GONE
            itemView.wednesdayWeek.visibility = View.GONE
            itemView.thursdayWeek.visibility = View.GONE
            itemView.fridayWeek.visibility = View.GONE
            itemView.saturdayWeek.visibility = View.GONE
            itemView.sundayWeek.visibility = View.GONE
            when (timeTable[0].dayWeek) {
                "ПН" -> {
                    itemView.mondayStartTime.text = timeTable[0].timeStart
                    itemView.mondayEndTime.text = timeTable[0].timeEnd
                    itemView.mondayWeek.visibility = View.VISIBLE
                }
                "ВТ" -> {
                    itemView.tuesdayStartTime.text = timeTable[0].timeStart
                    itemView.tuesdayEndTime.text = timeTable[0].timeEnd
                    itemView.tuesdayWeek.visibility = View.VISIBLE
                }
                "СР" -> {
                    itemView.wednesdayStartTime.text = timeTable[0].timeStart
                    itemView.wednesdayEndTime.text = timeTable[0].timeEnd
                    itemView.wednesdayWeek.visibility = View.VISIBLE
                }
                "ЧТ" -> {
                    itemView.thursdayStartTime.text = timeTable[0].timeStart
                    itemView.thursdayEndTime.text = timeTable[0].timeEnd
                    itemView.thursdayWeek.visibility = View.VISIBLE
                }
                "ПТ" -> {
                    itemView.fridayStartTime.text = timeTable[0].timeStart
                    itemView.fridayEndTime.text = timeTable[0].timeEnd
                    itemView.fridayWeek.visibility = View.VISIBLE
                }
                "СБ" -> {
                    itemView.saturdayStartTime.text = timeTable[0].timeStart
                    itemView.saturdayEndTime.text = timeTable[0].timeEnd
                    itemView.saturdayWeek.visibility = View.VISIBLE
                }
                "ВС" -> {
                    itemView.sundayStartTime.text = timeTable[0].timeStart
                    itemView.sundayEndTime.text = timeTable[0].timeEnd
                    itemView.sundayWeek.visibility = View.VISIBLE
                }
            }
        }
    }
}