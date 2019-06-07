package com.aurimteam.justhobby.user.main.notifications

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.NotificationResponse

class NotificationsOldAdapter : RecyclerView.Adapter<NotificationsHolder>() {

    private val notifications: MutableList<NotificationResponse> = mutableListOf()

    override fun getItemCount(): Int = notifications.size
    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        holder.bind(
            position == 0,
            position == itemCount - 1,
            notifications[position].title,
            notifications[position].description,
            notifications[position].day,
            notifications[position].month,
            notifications[position].time,
            notifications[position].new
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NotificationsHolder =
        NotificationsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_notification, parent, false)
        )

    fun onDataChange(notifications: List<NotificationResponse>) {
        this.notifications.clear()
        this.notifications.addAll(notifications)
        notifyDataSetChanged()
    }
}