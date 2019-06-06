package com.aurimteam.justhobby.user.main.home.user_courses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.*
import kotlinx.android.synthetic.main.fragment_card_user_course.view.*


class UserCoursesAdapter : RecyclerView.Adapter<UserCoursesHolder>() {

    private val userCoursesList: MutableList<GroupResponse> = mutableListOf()
    private val courseIncludedList: MutableMap<Long, CourseResponse> = mutableMapOf()
    private val companyIncludedList: MutableMap<Long, CompanyResponse> = mutableMapOf()
    private val isClickedList: MutableList<Boolean> = mutableListOf()
    override fun getItemCount(): Int = userCoursesList.size
    override fun onBindViewHolder(holder: UserCoursesHolder, position: Int) {
        holder.bind(
            courseIncludedList[userCoursesList[position].relationships.course.id]!!.attributes.title,
            userCoursesList[position].attributes.title,
            companyIncludedList[
                    courseIncludedList[userCoursesList[position].relationships.course.id]!!.relationships.company.id
            ]!!.attributes.title,
            courseIncludedList[userCoursesList[position].relationships.course.id]!!.attributes.address,
            userCoursesList[position].attributes.teacher,
            userCoursesList[position].relationships.timetable_near,
            isClickedList[position]
        )
        holder.itemView.cardUserCourseDays.setOnClickListener {
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
        isClickedList[position] = value
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserCoursesHolder =
        UserCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_user_course, parent, false)
        )

    fun onDataChange(userCourses: List<GroupResponse>, included: IncludedResponse) {
        userCoursesList.clear()
        userCoursesList.addAll(userCourses)
        courseIncludedList.clear()
        companyIncludedList.clear()
        if (included.courses != null) {
            for (item in included.courses) {
                courseIncludedList[item.id] = item
            }
        }
        if (included.companies != null) {
            for (item in included.companies) {
                companyIncludedList[item.id] = item
            }
        }
        for (item in userCoursesList) {
            isClickedList.add(userCoursesList.indexOf(item), false)
        }
        notifyDataSetChanged()
    }
}