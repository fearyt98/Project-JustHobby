package com.aurimteam.justhobby.user.main.notifications

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.response.NotificationResponse

class NotificationsAdapter : RecyclerView.Adapter<NotificationsHolder>() {

    private val notifications: MutableList<NotificationResponse> = mutableListOf()
    private val courseIncludedList: MutableMap<Long, CourseResponseR> = mutableMapOf()

    override fun getItemCount(): Int = notifications.size
    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        val item = notifications[position]
        val itemCourse = courseIncludedList[item.relationships.course.id]!!
        holder.bind(
            position == 0,
            position == itemCount - 1,
            itemCourse.attributes.title,
            item.attributes.text,
            item.attributes.created_at
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NotificationsHolder =
        NotificationsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_notification, parent, false)
        )

    fun onDataChange(notifications: List<NotificationResponse>, included: IncludedResponse) {
        this.notifications.clear()
        this.notifications.addAll(notifications)
        if (included.courses != null)
            for (item in included.courses)
                courseIncludedList[item.id] = item
        notifyDataSetChanged()
    }
}