package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import kotlinx.android.synthetic.main.fragment_card_course.view.*

class PopularCoursesAdapter : RecyclerView.Adapter<PopularCoursesHolder>() {

    private val coursesList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PopularCoursesHolder =
        PopularCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_course, parent, false)
        )

    override fun onBindViewHolder(holder: PopularCoursesHolder, position: Int) {
        holder.bind(
            coursesList[position].id,
            coursesList[position].attributes.title,
            coursesList[position].attributes.description,
            coursesList[position].attributes.address
        )
        holder.itemView.cardCourse.setOnClickListener { detailInfoCourse() }
        holder.itemView.cardCourseBtnBookmark.setOnClickListener { addBookmark() }
        holder.itemView.cardCourseBtnGeo.setOnClickListener { searchCourseOnMap() }
    }

    fun onDataChange(courses: List<CourseResponse>) {
        coursesList.clear()
        coursesList.addAll(courses)
        notifyDataSetChanged()
    }

    private fun detailInfoCourse() {
        Log.d("detailInfoCourse","granted")
    }

    private fun addBookmark() {
        Log.d("addBookmark","granted")
    }

    private fun searchCourseOnMap() {
        Log.d("searchCourseOnMap","granted")
    }
}