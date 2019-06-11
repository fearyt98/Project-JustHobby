package com.aurimteam.justhobby.user.main.notifications

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.card_notification.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotificationsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        first: Boolean,
        title: String,
        description: String,
        createdAt: Long
    ) {
        itemView.cardNotificationTitle.text = title
        itemView.cardNotificationDescription.text = description
        itemView.cardNotificationDateTime.text =
            SimpleDateFormat("d MMMM в HH:mm", Locale.getDefault()).format(Date(createdAt * 1000))
        if (first) {
            itemView.cardNotificationFirstLine.visibility = View.VISIBLE
        }
    }
}