package com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.user.company_info.course_info.CompanyInfoFragment
import com.aurimteam.justhobby.user.course_info.сourse_info.CourseInfoFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import kotlinx.android.synthetic.main.card_course.view.*

class NearUserCoursesAdapter : RecyclerView.Adapter<NearUserCoursesHolder>() {

    private val coursesList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onBindViewHolder(holder: NearUserCoursesHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.bind(
            coursesList[position].id,
            position == 0,
            position == itemCount - 1,
            coursesList[position].attributes.title,
            coursesList[position].attributes.description,
            coursesList[position].attributes.address
        )
        holder.itemView.cardCourse.setOnClickListener {
            detailInfoCourse(manager)
        }
        holder.itemView.cardCourseCompany.setOnClickListener {
            detailCompanyInfo(manager)
        }
        holder.itemView.cardCourseBtnBookmark.setOnClickListener { addBookmark() }
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NearUserCoursesHolder =
        NearUserCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_course, parent, false)
        )

    fun onDataChange(courses: List<CourseResponse>) {
        coursesList.clear()
        coursesList.addAll(courses)
        notifyDataSetChanged()
    }

    private fun detailInfoCourse(fm: FragmentManager) {
        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseInfoFragment())
            .commit()
    }

    private fun detailCompanyInfo(fm: FragmentManager) {
        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment,
                CompanyInfoFragment()
            )
            .commit()
    }

    private fun addBookmark() {

    }

    private fun searchCourseOnMap() {

    }
}