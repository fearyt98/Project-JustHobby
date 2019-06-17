package com.aurimteam.justhobby.user.main.home.user_groups

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.TimetableResponse
import kotlinx.android.synthetic.main.card_user_group.view.*

class UserGroupsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        titleCourse: String,
        titleGroup: String,
        titleCompany: String,
        address: String,
        teacher: String,
        timeTable: List<TimetableResponse>?,
        isClicked: Boolean
    ) {
        itemView.cardUserGroupTitle.text = "$titleCourse ($titleGroup)"
        itemView.cardUserGroupCompany.text = titleCompany
        itemView.cardUserGroupAddress.text = address
        itemView.cardUserGroupTeacher.text = teacher

        itemView.cardUserGroupDay2.visibility = View.GONE
        itemView.cardUserGroupDay3.visibility = View.GONE
        itemView.cardUserGroupDay4.visibility = View.GONE
        itemView.cardUserGroupDay5.visibility = View.GONE
        itemView.cardUserGroupDay6.visibility = View.GONE
        itemView.cardUserGroupDay7.visibility = View.GONE

        if (timeTable != null) {
            itemView.cardUserGroupDay1Str.text = dayWeekStr(timeTable[0].day_week)
            itemView.cardUserGroupDay1StartTime.text = intToTime(timeTable[0].attributes.time_start)
            itemView.cardUserGroupDay1EndTime.text =
                intToTime(timeTable[0].attributes.time_start + timeTable[0].attributes.duration)
        }

        toggleTimetable(timeTable, isClicked)
    }

    fun toggleTimetable(timeTable: List<TimetableResponse>?, isClicked: Boolean) {
        if (isClicked && timeTable != null) {
            for (item in timeTable) {
                when (timeTable.indexOf(item)) {
                    1 -> {
                        itemView.cardUserGroupDay2Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserGroupDay2StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserGroupDay2EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserGroupDay2.visibility = View.VISIBLE
                    }
                    2 -> {
                        itemView.cardUserGroupDay3Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserGroupDay3StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserGroupDay3EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserGroupDay3.visibility = View.VISIBLE
                    }
                    3 -> {
                        itemView.cardUserGroupDay4Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserGroupDay4StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserGroupDay4EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserGroupDay4.visibility = View.VISIBLE
                    }
                    4 -> {
                        itemView.cardUserGroupDay5Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserGroupDay5StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserGroupDay5EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserGroupDay5.visibility = View.VISIBLE
                    }
                    5 -> {
                        itemView.cardUserGroupDay6Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserGroupDay6StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserGroupDay6EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserGroupDay6.visibility = View.VISIBLE
                    }
                    6 -> {
                        itemView.cardUserGroupDay7Str.text = dayWeekStr(item.day_week)
                        itemView.cardUserGroupDay7StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardUserGroupDay7EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardUserGroupDay7.visibility = View.VISIBLE
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun intToTime(intTime: Int): String {
        val intTimeDay = intTime % 1440
        val hour = Math.floor((intTimeDay / 60).toDouble()).toInt()
        val minute = intTimeDay % 60
        var hourStr = "$hour"
        if (hour < 10) hourStr = "0$hour"

        var minuteStr = "$minute"
        if (minute < 10) minuteStr = "0$minute"
        return "$hourStr:$minuteStr"
    }

    private fun dayWeekStr(dayWeek: Int): String {
        return itemView.context.resources.getString(
            when (dayWeek) {
                0 -> R.string.short_monday
                1 -> R.string.short_tuesday
                2 -> R.string.short_wednesday
                3 -> R.string.short_thursday
                4 -> R.string.short_friday
                5 -> R.string.short_saturday
                6 -> R.string.short_sunday
                else -> R.string.short_monday
            }
        )
    }
}