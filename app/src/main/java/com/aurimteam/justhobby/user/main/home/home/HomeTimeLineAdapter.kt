package com.aurimteam.justhobby.user.main.home.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.EventResponse

class HomeTimeLineAdapter : RecyclerView.Adapter<TimeLineHolder>() {

    private val timeLineEvents: MutableList<EventResponse> = mutableListOf()

    override fun getItemCount(): Int = timeLineEvents.size
    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        holder.bind(
            position == this.itemCount - 1,
            timeLineEvents[position].attributes.time_start,
            timeLineEvents[position].attributes.title_company,
            timeLineEvents[position].attributes.title_course,
            timeLineEvents[position].attributes.teacher,
            timeLineEvents[position].attributes.title_company,
            timeLineEvents[position].attributes.address,
            timeLineEvents[position].attributes.category_slug
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TimeLineHolder =
        TimeLineHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_event, parent, false)
        )

    fun onDataChange(events: List<EventResponse>) {
        this.timeLineEvents.clear()
        this.timeLineEvents.addAll(events)
        notifyDataSetChanged()
    }
}