package com.aurimteam.justhobby.user.main.home.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.EventResponse
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import kotlinx.android.synthetic.main.card_event.view.*
import java.util.*

class HomeTimelineAdapter : RecyclerView.Adapter<HomeTimelineHolder>() {

    private var isNow: Boolean = true
    private var itemRemove: MutableList<Int> = mutableListOf()
    private var timeLineEvents: MutableList<EventResponse> = mutableListOf()

    override fun getItemCount(): Int = timeLineEvents.size
    override fun onBindViewHolder(holder: HomeTimelineHolder, position: Int, payloads: MutableList<Any>) {
        val item = timeLineEvents[position]
        if ((item.attributes.time_start + item.attributes.duration) < dayTime() && isNow)
            itemRemove.add(position)
        else if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            holder.bindTime(
                position == itemCount - 1,
                isNow,
                timeLineEvents[position].attributes.time_start,
                timeLineEvents[position].attributes.duration
            )
    }

    override fun onBindViewHolder(holder: HomeTimelineHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        val item = timeLineEvents[position]
        holder.bind(
            position == itemCount - 1,
            isNow,
            timeLineEvents[position].attributes.time_start,
            timeLineEvents[position].attributes.duration,
            timeLineEvents[position].attributes.title_course,
            timeLineEvents[position].attributes.title_group,
            timeLineEvents[position].attributes.teacher,
            timeLineEvents[position].attributes.title_company,
            timeLineEvents[position].attributes.address,
            timeLineEvents[position].attributes.category_slug
        )
        holder.itemView.cardTimeline.setOnClickListener { detailInfoCourse(manager, item.id) }
        holder.itemView.cardTimelineAddress.setOnClickListener {
            openMap(
                holder.itemView.context,
                timeLineEvents[position]
            )
        }
    }

    private fun dayTime(): Int = (
            GregorianCalendar().get(Calendar.HOUR_OF_DAY) * 60 + GregorianCalendar().get(Calendar.MINUTE)
            )

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HomeTimelineHolder =
        HomeTimelineHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_event, parent, false)
        )

    fun onDataChange(events: List<EventResponse>, isNow: Boolean) {
        timeLineEvents.clear()
        timeLineEvents.addAll(events)
        if (isNow)
            for (item in events)
                if (item.attributes.time_start + item.attributes.duration < dayTime())
                    timeLineEvents.remove(item)
        notifyDataSetChanged()
        this.isNow = isNow
    }

    fun removeIfIs() {
        for (position in itemRemove) removeItem(position)
        itemRemove.clear()
    }

    private fun detailInfoCourse(fm: FragmentManager, courseId: Long) {
        val bundle = Bundle()
        bundle.putString("course_id", courseId.toString())
        val courseInfoFragment = CourseInfoFragment()
        courseInfoFragment.arguments = bundle

        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, courseInfoFragment)
            .commit()
    }

    private fun removeItem(position: Int) {
        timeLineEvents.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, timeLineEvents.count())
    }

    private fun openMap(context: Context, event: EventResponse) {
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:${event.attributes.lat},${event.attributes.lon}"))
        context.startActivity(mapIntent)
    }
}