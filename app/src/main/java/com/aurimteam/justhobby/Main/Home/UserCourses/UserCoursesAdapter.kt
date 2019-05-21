package com.aurimteam.justhobby.Main.Home.UserCourses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.UserCourseResponse
import kotlinx.android.synthetic.main.fragment_card_user_course.view.*


class UserCoursesAdapter : RecyclerView.Adapter<UserCoursesHolder>() {

    private val userCoursesList: MutableList<UserCourseResponse> = mutableListOf()
    private val isClickedList: MutableList<Boolean> = mutableListOf()
    override fun getItemCount(): Int = userCoursesList.size
    override fun onBindViewHolder(holder: UserCoursesHolder, position: Int) {
        holder.bind(
            userCoursesList[position].title,
            userCoursesList[position].description,
            userCoursesList[position].address,
            userCoursesList[position].tutor,
            userCoursesList[position].timeTable,
            isClickedList[position]
        )
        holder.itemView.daysOfWeek.setOnClickListener {
            if (!isClickedList[position]) {
                setIsClicked(position, true)
                notifyItemChanged(position)
            } else {
                setIsClicked(position, false)
                notifyItemChanged(position)
            }
        }
    }

    private fun setIsClicked(position: Int, value: Boolean) {
        this.isClickedList[position] = value
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserCoursesHolder =
        UserCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_user_course, parent, false)
        )

    fun onDataChange(userCourses: List<UserCourseResponse>) {
        this.userCoursesList.clear()
        this.userCoursesList.addAll(userCourses)
        for (item in this.userCoursesList) {
            isClickedList.add(this.userCoursesList.indexOf(item), false)
        }
        notifyDataSetChanged()
    }
}