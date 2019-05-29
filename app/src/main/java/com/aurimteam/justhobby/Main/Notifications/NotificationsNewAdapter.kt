package com.aurimteam.justhobby.Main.Notifications

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.NotificationResponse

class NotificationsNewAdapter : RecyclerView.Adapter<NotificationsHolder>() {

    private val notifications: MutableList<NotificationResponse> = mutableListOf()

    override fun getItemCount(): Int = notifications.size
    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        holder.bind(
            position == 0,
            false,
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
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_notification, parent, false)
        )

    fun onDataChange(notifications: List<NotificationResponse>) {
        this.notifications.clear()
        this.notifications.addAll(notifications)
        notifyDataSetChanged()
    }
}