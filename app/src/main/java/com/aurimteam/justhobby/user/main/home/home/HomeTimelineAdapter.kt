package com.aurimteam.justhobby.user.main.home.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.EventResponse
import java.util.*

class HomeTimelineAdapter : RecyclerView.Adapter<TimelineHolder>() {

    private var isNow: Boolean = true
    private var itemRemove: MutableList<Int> = mutableListOf()
    private var timeLineEvents: MutableList<EventResponse> = mutableListOf()

    override fun getItemCount(): Int = timeLineEvents.size
    override fun onBindViewHolder(holder: TimelineHolder, position: Int, payloads: MutableList<Any>) {
        val item = timeLineEvents[position]
        if ((item.attributes.time_start + item.attributes.duration) < dayTime() && isNow)
            itemRemove.add(position)
        else if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            holder.bindTime(
                position == itemCount - 1,
                isNow,
                timeLineEvents[position].attributes.time_start,
                timeLineEvents[position].attributes.duration
            )
    }


    override fun onBindViewHolder(holder: TimelineHolder, position: Int) {
        holder.bind(
            position == itemCount - 1,
            isNow,
            timeLineEvents[position].attributes.time_start,
            timeLineEvents[position].attributes.duration,
            timeLineEvents[position].attributes.title_course,
            timeLineEvents[position].attributes.title_group,
            timeLineEvents[position].attributes.teacher,
            timeLineEvents[position].attributes.title_company,
            timeLineEvents[position].attributes.address,
            timeLineEvents[position].attributes.category_slug
        )
    }

    private fun dayTime(): Int = (
            GregorianCalendar().get(Calendar.HOUR_OF_DAY) * 60 + GregorianCalendar().get(Calendar.MINUTE)
            )


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TimelineHolder =
        TimelineHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_event, parent, false)
        )

    fun onDataChange(events: List<EventResponse>, isNow: Boolean) {
        timeLineEvents.clear()
        timeLineEvents.addAll(events)
        if (isNow)
            for (item in events)
                if (item.attributes.time_start + item.attributes.duration < dayTime())
                    timeLineEvents.remove(item)
        notifyDataSetChanged()
        isNow = isNow
    }

    fun removeIfIs() {
        for (position in itemRemove) removeItem(position)
        itemRemove.clear()
    }

    private fun removeItem(position: Int) {
        timeLineEvents.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, timeLineEvents.count())
    }
}