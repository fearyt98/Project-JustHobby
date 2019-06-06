package com.aurimteam.justhobby.user.main.home.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.fragment_card_event.view.*
import java.util.*


class TimelineHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        last: Boolean,
        isNow: Boolean,
        time: Int,
        duration: Int,
        titleCourse: String,
        titleGroup: String,
        teacher: String,
        titleCompany: String,
        address: String,
        category: String
    ) {
        bindTime(last, isNow, time, duration)

        itemView.cardTimelineTime.text = intToTime(time)
        itemView.cardTimelineTitle.text = "$titleCourse($titleGroup)"
        itemView.cardTimelineTeacher.text = teacher
        itemView.cardTimelineCompany.text = titleCompany
        itemView.cardTimelineAddress.text = address

        val padding: List<Int> = listOf(
            itemView.cardTimelineBg.paddingLeft, itemView.cardTimelineBg.paddingTop,
            itemView.cardTimelineBg.paddingRight, itemView.cardTimelineBg.paddingBottom
        )
        when (category) {
            "languages" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_languages)
            "sports" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_sports)
            "musics" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_dances)
            "it" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_it)
            "dances" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_dances)
            "art_design" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_art_design)
            "craft" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_craft)
            "designing" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_designing)
            "literature" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_literature)
            "fashion_style" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_fashion_style)
            "entertainment_arts" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_entertainment_arts)
            else -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_languages)
        }
        itemView.cardTimelineBg.setPadding(padding[0], padding[1], padding[2], padding[3])
    }

    fun bindTime(
        last: Boolean,
        isNow: Boolean,
        time: Int,
        duration: Int
    ) {
        itemView.cardTimelineLineLast.visibility = if (last) View.INVISIBLE else View.VISIBLE
        itemView.cardTimelineCountdown.visibility = if (isNow) View.VISIBLE else View.INVISIBLE
        if (isNow)
            if (time <= dayTime()) {
                val timeTill = intToTime(time + duration - dayTime())
                itemView.cardTimelineCountdown.text =
                    String.format(itemView.context.resources.getString(R.string.before_end_of_event), timeTill)
            } else {
                val hour = intToHour(time - dayTime())
                val stringId = termNum(
                    hour,
                    R.string.before_event_term5toMoreAnd11to14,
                    R.string.before_event_term0,
                    R.string.before_event_term1,
                    R.string.before_event_term2to4,
                    R.string.before_event_term5toMoreAnd11to14
                )
                itemView.cardTimelineCountdown.text =
                    String.format(itemView.context.resources.getString(stringId), hour)
            }
    }

    private fun dayTime(): Int {
        return GregorianCalendar().get(Calendar.HOUR_OF_DAY) * 60 + GregorianCalendar().get(Calendar.MINUTE)
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

    private fun intToHour(intTime: Int): Int {
        var hour = Math.floor((intTime / 60).toDouble()).toInt()
        val minute = intTime % 60
        if (minute >= 30) hour++
        return hour
    }

    private fun termNum(
        num: Int,
        term11to14: Int,
        term0: Int,
        term1: Int,
        term2to4: Int,
        term5toMore: Int
    ): Int {
        var term = term11to14
        if (num <= 10 || num >= 15) {
            val number = num.toString().substring(num.toString().length - 1).toInt()
            if (number == 0) {
                term = term0; }
            if (number == 1) {
                term = term1; }
            if (number > 1) {
                term = term2to4; }
            if (number > 4) {
                term = term5toMore; }
        }
        return term
    }
}