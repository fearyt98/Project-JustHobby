package com.aurimteam.justhobby.user.company_info.company_info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse

class CompanyInfoAdapter(private var presenter: CompanyInfoPresenter) :
    RecyclerView.Adapter<CompanyInfoCoursesHolder>() {

    private var companyCoursesList: MutableList<CourseResponse> = mutableListOf()
    override fun getItemCount(): Int = companyCoursesList.size

    override fun onBindViewHolder(holder: CompanyInfoCoursesHolder, position: Int) {
        holder.bind(
            companyCoursesList[position].id.toLong(),
            companyCoursesList[position].attributes.title,
            companyCoursesList[position].attributes.description,
            companyCoursesList[position].attributes.address
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CompanyInfoCoursesHolder =
        CompanyInfoCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_course, parent, false)
        )

    fun onDataChange(courses: List<CourseResponse>) {
        companyCoursesList.clear()
        companyCoursesList.addAll(courses)
        notifyDataSetChanged()
    }
}