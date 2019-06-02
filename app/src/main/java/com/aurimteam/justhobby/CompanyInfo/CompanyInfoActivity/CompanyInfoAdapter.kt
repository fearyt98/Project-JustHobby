package com.aurimteam.justhobby.CompanyInfo.CompanyInfoActivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse

class CompanyInfoAdapter(private var presenter: CompanyInfoPresenter) :
    RecyclerView.Adapter<CompanyInfoCoursesHolder>() {

    private var companyCoursesList: MutableList<CourseResponse> = mutableListOf()
    override fun getItemCount(): Int = companyCoursesList.size

    override fun onBindViewHolder(holder: CompanyInfoCoursesHolder, position: Int) {
        holder.bind(
            companyCoursesList[position].id,
            companyCoursesList[position].title,
            companyCoursesList[position].description,
            companyCoursesList[position].address,
            companyCoursesList[position].company,
            companyCoursesList[position].category
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CompanyInfoCoursesHolder =
        CompanyInfoCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
        )

    fun onDataChange(courses: List<CourseResponse>) {
        this.companyCoursesList.clear()
        this.companyCoursesList.addAll(courses)
        notifyDataSetChanged()
    }
}