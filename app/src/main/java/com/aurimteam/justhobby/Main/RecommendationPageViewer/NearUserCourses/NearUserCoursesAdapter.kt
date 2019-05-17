package com.aurimteam.justhobby.Main.RecommendationPageViewer.NearUserCourses

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity.CourseInfoFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class NearUserCoursesAdapter : RecyclerView.Adapter<NearUserCoursesHolder>() {

    private val coursesList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onBindViewHolder(holder: NearUserCoursesHolder, position: Int) {
        holder.bind(
            coursesList[position].id,
            position == 0,
            position == this.itemCount - 1,
            coursesList[position].title,
            coursesList[position].description,
            coursesList[position].address,
            coursesList[position].company,
            coursesList[position].category
        )
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.itemView.cardCourse.setOnClickListener {
            detailInfoCourse(manager)
        }
        holder.itemView.cardCourseBtnBookmark.setOnClickListener { addBookmark() }
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NearUserCoursesHolder =
        NearUserCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
        )

    fun onDataChange(courses: List<CourseResponse>) {
        coursesList.clear()
        coursesList.addAll(courses)
        notifyDataSetChanged()
    }

    private fun detailInfoCourse(fm: FragmentManager) {
        fm!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseInfoFragment())
            .commit()
    }

    private fun addBookmark() {

    }

    private fun searchCourseOnMap() {

    }
}