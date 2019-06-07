package com.aurimteam.justhobby.user.main.notifications

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.card_notification.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotificationsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        first: Boolean,
        last: Boolean,
        title: String,
        description: String,
        day: Int,
        month: String,
        time: Long,
        new: Boolean
    ) {
        var date = Date(time)
        itemView.cardNotificationTitle.text = title
        itemView.cardNotificationDescription.text = description
        var dateFormat = SimpleDateFormat("d")
        itemView.cardNotificationDay.text = dateFormat.format(date)
        dateFormat = SimpleDateFormat("MMMM")
        itemView.cardNotificationMonth.text = dateFormat.format(date)
        dateFormat = SimpleDateFormat("HH:mm")
        itemView.cardNotificationTime.text = dateFormat.format(date)
        if(first) {
            itemView.cardNotificationFirstLine.visibility = View.VISIBLE
        }
        if(last) {
            itemView.cardNotificationLastLine.visibility = View.INVISIBLE
        }
    }
}