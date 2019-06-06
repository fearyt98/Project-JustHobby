package com.aurimteam.justhobby.user.course_info.сourse_info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.GroupResponse

class CourseInfoAdapter : RecyclerView.Adapter<CourseInfoGroupsHolder>() {

    private val courseGroupsList: MutableList<GroupResponse> = mutableListOf()

    override fun getItemCount(): Int = courseGroupsList.size
    override fun onBindViewHolder(holder: CourseInfoGroupsHolder, position: Int) {
        holder.bind(
            courseGroupsList[position].id,
            courseGroupsList[position].attributes.title,
            courseGroupsList[position].attributes.teacher,
            courseGroupsList[position].attributes.price
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseInfoGroupsHolder =
        CourseInfoGroupsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_group, parent, false)
        )

    fun onDataChange(groups: List<GroupResponse>) {
        courseGroupsList.clear()
        courseGroupsList.addAll(groups)
        notifyDataSetChanged()
    }
}