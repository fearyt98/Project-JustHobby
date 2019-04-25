package com.aurimteam.justhobby.Main.HomeActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_card_timeline.view.*

class TimeLineHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        id: Int,
        last: Boolean,
        time: String,
        title: String,
        tutor: String,
        organization: String,
        address: String,
        category: String
    ) {
        itemView.cardTimelineLineLast.visibility = if (last) View.INVISIBLE else View.VISIBLE
        itemView.cardTimelineTime.text = time
        itemView.cardTimelineTitle.text = title
        itemView.cardTimelineTeacher.text = tutor
        itemView.cardTimelineOrganization.text = organization
        itemView.cardTimelineAddress.text = address
    }
}