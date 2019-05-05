package com.aurimteam.justhobby.Main.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimelineResponses

class HomeTimeLineAdapter : RecyclerView.Adapter<TimeLineHolder>() {

    private val timeLineEvents: MutableList<TimelineResponses> = mutableListOf()

    override fun getItemCount(): Int = timeLineEvents.size
    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        holder.bind(
            timeLineEvents[position].id,
            position == this.itemCount - 1,
            timeLineEvents[position].time,
            timeLineEvents[position].title,
            timeLineEvents[position].tutor,
            timeLineEvents[position].organization,
            timeLineEvents[position].address,
            timeLineEvents[position].category
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TimeLineHolder =
        TimeLineHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_card_timeline, parent, false)
        )

    fun onDataChange(events: List<TimelineResponses>) {
        this.timeLineEvents.clear()
        this.timeLineEvents.addAll(events)
        notifyDataSetChanged()
    }
}