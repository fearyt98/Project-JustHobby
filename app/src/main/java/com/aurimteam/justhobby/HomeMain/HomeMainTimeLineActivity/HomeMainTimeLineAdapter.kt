package com.aurimteam.justhobby.HomeMain.HomeMainTimeLineActivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.TimeLineEventResponse

class HomeMainTimeLineAdapter : RecyclerView.Adapter<TimeLineHolder>() {

    private val timeLineEvents: MutableList<TimeLineEventResponse> = mutableListOf()

    override fun getItemCount(): Int = timeLineEvents.size
    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        holder.bind(
            timeLineEvents[position].id,
            timeLineEvents[position].time,
            timeLineEvents[position].title,
            timeLineEvents[position].tutor,
            timeLineEvents[position].organization
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TimeLineHolder =
        TimeLineHolder(
            LayoutInflater.from(parent.context).
                inflate(R.layout.activity_home_main_timeline_event_item, parent, false)
        )
    fun onDataChange(events: List<TimeLineEventResponse>){
        this.timeLineEvents.clear()
        this.timeLineEvents.addAll(events)
        notifyDataSetChanged()
    }
}