package com.aurimteam.justhobby.group

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.TimetableResponse
import kotlinx.android.synthetic.main.card_group.view.*

class GroupHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        title: String,
        description: String,
        teacher: String,
        status: Boolean,
        typePayment: Int,
        price: Int,
        sex: Int,
        ageMax: Int,
        ageMin: Int,
        timeTable: List<TimetableResponse>?,
        user: Boolean?
    ) {
        itemView.cardGroupTitle.text = title
        itemView.cardGroupTeacher.text = teacher
        itemView.cardGroupStatus.setImageResource(
            if (status) R.drawable.ic_success_24dp else R.drawable.ic_fail_24dp
        )
        when (sex) {
            0 -> itemView.cardGroupSex.visibility = View.VISIBLE
            1 -> itemView.cardGroupSexMale.visibility = View.VISIBLE
            2 -> itemView.cardGroupSexFemale.visibility = View.VISIBLE
        }
        itemView.cardGroupPrice.text =
            String.format(
                itemView.context.resources.getString(
                    when (typePayment) {
                        1 -> R.string.payment_month
                        2 -> R.string.payment_course
                        else -> R.string.payment_one
                    }
                ),
                price
            )
        itemView.cardGroupAgePeople.text = "$ageMin - $ageMax"

       if(description == "")
            itemView.cardGroupBtnInfo.visibility = View.GONE

        if (timeTable != null)
            for (item in timeTable) {
                when (item.day_week) {
                    0 -> {
                        itemView.cardGroupDay1StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay1EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay1.alpha = 1f
                        itemView.cardGroupDay1None.visibility = View.GONE
                    }
                    1 -> {
                        itemView.cardGroupDay2StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay2EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay2.alpha = 1f
                        itemView.cardGroupDay2None.visibility = View.GONE
                    }
                    2 -> {
                        itemView.cardGroupDay3StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay3EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay3.alpha = 1f
                        itemView.cardGroupDay3None.visibility = View.GONE
                    }
                    3 -> {
                        itemView.cardGroupDay4StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay4EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay4.alpha = 1f
                        itemView.cardGroupDay4None.visibility = View.GONE
                    }
                    4 -> {
                        itemView.cardGroupDay5StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay5EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay5.alpha = 1f
                        itemView.cardGroupDay5None.visibility = View.GONE
                    }
                    5 -> {
                        itemView.cardGroupDay6StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay6EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay6.alpha = 1f
                        itemView.cardGroupDay6None.visibility = View.GONE
                    }
                    6 -> {
                        itemView.cardGroupDay7StartTime.text = intToTime(item.attributes.time_start)
                        itemView.cardGroupDay7EndTime.text =
                            intToTime(item.attributes.time_start + item.attributes.duration)
                        itemView.cardGroupDay7.alpha = 1f
                        itemView.cardGroupDay7None.visibility = View.GONE
                    }
                    else -> {
                    }
                }
            }

        toggleBtnAddDelete(user)
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

    fun toggleBtnAddDelete(user: Boolean?) {
        if (user != null && user) {
            itemView.cardGroupBtnAdd.visibility = View.GONE
            itemView.cardGroupBtnDelete.visibility = View.VISIBLE
        } else {
            itemView.cardGroupBtnAdd.visibility = View.VISIBLE
            itemView.cardGroupBtnDelete.visibility = View.GONE
        }
    }
}