package com.aurimteam.justhobby.HomeMain.HomeMainTimeLineActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_home_main_timeline_event_item.view.*

class TimeLineHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Int,
        time: String,
        title: String,
        tutor: String,
        organization: String
    ) {
        itemView.timeline_time.text = time
        itemView.timeline_title.text = title
        itemView.timeline_tutor.text = tutor
        itemView.timeline_organization.text = organization

    }
}