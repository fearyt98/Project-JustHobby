package com.aurimteam.justhobby.user.main.home.user_courses

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.TimetableResponse
import kotlinx.android.synthetic.main.fragment_card_user_course.view.*

class UserCoursesHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        titleCourse: String,
        titleGroup: String,
        titleCompany: String,
        address: String,
        teacher: String,
        timeTable: List<TimetableResponse>,
        isClicked: Boolean
    ) {
        itemView.cardUserCourseTitle.text = "$titleCourse ($titleGroup)"
        itemView.cardUserCourseCompany.text = titleCompany
        itemView.cardUserCourseAddress.text = address
        itemView.cardUserCourseTeacher.text = teacher

        itemView.cardUserCourseDay2.visibility = View.GONE
        itemView.cardUserCourseDay3.visibility = View.GONE
        itemView.cardUserCourseDay4.visibility = View.GONE
        itemView.cardUserCourseDay5.visibility = View.GONE
        itemView.cardUserCourseDay6.visibility = View.GONE
        itemView.cardUserCourseDay7.visibility = View.GONE
        itemView.cardUserCourseDay1Str.text = dayWeekStr(timeTable[0].day_week)
        itemView.cardUserCourseDay1StartTime.text = intToTime(timeTable[0].attributes.time_start)
        itemView.cardUserCourseDay1EndTime.text =
            intToTime(timeTable[0].attributes.time_start + timeTable[0].attributes.duration)

        if (isClicked) {
            for (item in timeTable) {
                when (timeTable.indexOf(item)) {
                    1 -> {
                        itemView.cardUserCourseDay2Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserCourseDay2StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserCourseDay2EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserCourseDay2.visibility = View.VISIBLE
                    }
                    2 -> {
                        itemView.cardUserCourseDay3Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserCourseDay3StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserCourseDay3EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserCourseDay3.visibility = View.VISIBLE
                    }
                    3 -> {
                        itemView.cardUserCourseDay4Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserCourseDay4StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserCourseDay4EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserCourseDay4.visibility = View.VISIBLE
                    }
                    4 -> {
                        itemView.cardUserCourseDay5Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserCourseDay5StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserCourseDay5EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserCourseDay5.visibility = View.VISIBLE
                    }
                    5 -> {
                        itemView.cardUserCourseDay6Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserCourseDay6StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserCourseDay6EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserCourseDay6.visibility = View.VISIBLE
                    }
                    6 -> {
                        itemView.cardUserCourseDay7Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserCourseDay7StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserCourseDay7EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserCourseDay7.visibility = View.VISIBLE
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun intToTime(intTime: Int): String {
        val hour = Math.floor((intTime / 60).toDouble()).toInt()
        val minute = intTime % 60
        var hourStr = "$hour"
        if (hour < 10) hourStr = "0$hour"

        var minuteStr = "$minute"
        if (minute < 10) minuteStr = "0$minute"
        return "$hourStr:$minuteStr"
    }

    private fun dayWeekStr(dayWeek: Int): String {
        return itemView.context.resources.getString(when (dayWeek) {
            0 -> R.string.short_monday
            1 -> R.string.short_tuesday
            2 -> R.string.short_wednesday
            3 -> R.string.short_thursday
            4 -> R.string.short_friday
            5 -> R.string.short_saturday
            6 -> R.string.short_sunday
            else -> R.string.short_monday
        })
    }
}