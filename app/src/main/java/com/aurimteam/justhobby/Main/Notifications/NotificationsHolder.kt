package com.aurimteam.justhobby.Main.Notifications

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_card_notification.view.*
import java.sql.Timestamp

class NotificationsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        title: String,
        description: String,
        day: Int,
        month: String,
        time: Timestamp,
        new: Boolean
    ) {
        itemView.notificationTitle.text = title
        itemView.notificationDescription.text = description
        itemView.notificationDay.text = day.toString()
        itemView.notificationMonth.text = month

    }
}