package com.aurimteam.justhobby.user.main.notifications

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import com.aurimteam.justhobby.response.NotificationResponse
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import kotlinx.android.synthetic.main.card_notification.view.*

class NotificationsAdapter : RecyclerView.Adapter<NotificationsHolder>() {

    private val notifications: MutableList<NotificationResponse> = mutableListOf()
    private val courseIncludedList: MutableMap<Long, CourseResponseR> = mutableMapOf()

    override fun getItemCount(): Int = notifications.size
    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val item = notifications[position]
        val itemCourse = courseIncludedList[item.relationships.course.id]!!
        holder.bind(
            position == 0,
            itemCourse.attributes.title,
            item.attributes.text,
            item.attributes.created_at
        )
        holder.itemView.notificationBody.setOnClickListener { detailInfoCourse(manager, itemCourse) }
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

    private fun detailInfoCourse(fm: FragmentManager, course: CourseResponseR) {
        val bundle = Bundle()
        bundle.putString("course_id", course.id.toString())
        val courseInfoFragment = CourseInfoFragment()
        courseInfoFragment.arguments = bundle

        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, courseInfoFragment)
            .commit()
    }
}