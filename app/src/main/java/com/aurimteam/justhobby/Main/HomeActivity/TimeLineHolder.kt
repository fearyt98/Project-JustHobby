package com.aurimteam.justhobby.Main.HomeActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
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
        val pL = itemView.cardTimelineBg.paddingLeft
        val pT = itemView.cardTimelineBg.paddingTop
        val pR = itemView.cardTimelineBg.paddingRight
        val pB = itemView.cardTimelineBg.paddingBottom
        when (category) {
            "dance" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_dance)
            "languages" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_languages)
            "sport" -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_sport)
            else -> itemView.cardTimelineBg.setBackgroundResource(R.drawable.card_timeline_bg_sport)
        }
        itemView.cardTimelineBg.setPadding(pL, pT, pR, pB)
        //itemView.cardTimelineBg.setPadding(17, 13, 17, 9);
        //val dataFormatTime = SimpleDateFormat("HH:mm")
        //val currentTimeString = dataFormatTime.format(Date())
        //val ttime = dataFormatTime.format(time)
        //itemView.cardTimelineCountdown.text = currentTimeString
    }
}