package com.aurimteam.justhobby.user.company_info.company_courses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse

class CompanyCoursesAdapter : RecyclerView.Adapter<CompanyCoursesHolder>() {

    private var companyCoursesList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = companyCoursesList.size
    override fun onBindViewHolder(holder: CompanyCoursesHolder, position: Int) {
        holder.bind(
            /*companyCoursesList[position].type,
            companyCoursesList[position].id,
            companyCoursesList[position].attributes,
            companyCoursesList[position].relationships*/

        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CompanyCoursesHolder =
        CompanyCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
        )

    fun onDataChange(courses: List<CourseResponse>) {
        companyCoursesList.clear()
        companyCoursesList.addAll(courses)
        notifyDataSetChanged()
    }
}