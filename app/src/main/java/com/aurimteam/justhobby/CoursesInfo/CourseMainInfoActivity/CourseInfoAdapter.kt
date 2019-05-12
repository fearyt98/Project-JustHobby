package com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.GroupResponse

class CourseInfoAdapter : RecyclerView.Adapter<CourseGroupHolder>() {

    private val courseGroupsList: MutableList<GroupResponse> = mutableListOf()

    override fun getItemCount(): Int = courseGroupsList.size
    override fun onBindViewHolder(holder: CourseGroupHolder, position: Int) {
        holder.bind(
            courseGroupsList[position].id,
            courseGroupsList[position].title,
            courseGroupsList[position].tutor,
            courseGroupsList[position].price
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseGroupHolder =
        CourseGroupHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_group, parent, false)
        )

    fun onDataChange(groups: List<GroupResponse>) {
        this.courseGroupsList.clear()
        this.courseGroupsList.addAll(groups)
        notifyDataSetChanged()
    }
}