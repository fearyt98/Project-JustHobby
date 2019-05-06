package com.aurimteam.justhobby.Main.Notifications

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_notification.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class NotificationsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        first: Boolean,
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

    }
}