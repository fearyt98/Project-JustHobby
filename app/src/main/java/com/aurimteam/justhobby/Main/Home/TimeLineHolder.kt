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
}