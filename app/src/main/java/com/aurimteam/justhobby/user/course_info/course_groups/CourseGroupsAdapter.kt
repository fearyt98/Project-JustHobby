package com.aurimteam.justhobby.user.course_info.course_groups

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.GroupResponse

class CourseGroupsAdapter : RecyclerView.Adapter<CourseGroupsHolder>() {

    private val courseAllGroupsList: MutableList<GroupResponse> = mutableListOf()

    override fun getItemCount(): Int = courseAllGroupsList.size
    override fun onBindViewHolder(holder: CourseGroupsHolder, position: Int) {
        holder.bind(
            courseAllGroupsList[position].id,
            courseAllGroupsList[position].title,
            courseAllGroupsList[position].teacher,
            courseAllGroupsList[position].price
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseGroupsHolder =
        CourseGroupsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_group, parent, false)
        )

    fun onDataChange(courseAllGroups: List<GroupResponse>) {
        this.courseAllGroupsList.clear()
        this.courseAllGroupsList.addAll(courseAllGroups)
        notifyDataSetChanged()
    }
}