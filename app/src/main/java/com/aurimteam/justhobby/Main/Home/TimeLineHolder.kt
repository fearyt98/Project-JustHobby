package com.aurimteam.justhobby.Main.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.fragment_card_timeline.view.*


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
        val padding: List<Int> = listOf(
            itemView.cardTimelineBg.paddingLeft, itemView.cardTimelineBg.paddingTop,
            itemView.cardTimelineBg.paddingRight, itemView.cardTimelineBg.paddingBottom
        )
        when (category) {
            "dance" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_dance)
            "languages" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_languages)
            "sport" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_sport)
            else -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_sport)
        }
        itemView.cardTimelineBg.setPadding(padding[0], padding[1], padding[2], padding[3])
    }
}