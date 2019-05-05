package com.aurimteam.justhobby.Main.RecommendationPageViewer.NearUserCourses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.activity_card_course.view.*

class NearUserCoursesAdapter : RecyclerView.Adapter<NearUserCoursesHolder>() {

    private val coursesList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onBindViewHolder(holder: NearUserCoursesHolder, position: Int) {
        holder.bind(
            coursesList[position].id,
            coursesList[position].title,
            coursesList[position].description,
            coursesList[position].address,
            coursesList[position].company,
            coursesList[position].category
        )
        holder.itemView.cardCourse.setOnClickListener { detailInfoCourse() }
        holder.itemView.btnBookmarkCourse.setOnClickListener { addBookmark() }
        holder.itemView.btnGeoCourse.setOnClickListener { searchCourseOnMap() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NearUserCoursesHolder =
        NearUserCoursesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_card_course, parent, false)
        )

    fun onDataChange(courses: List<CourseResponse>) {
        coursesList.clear()
        coursesList.addAll(courses)
        notifyDataSetChanged()
    }
    private fun detailInfoCourse(){

    }
    private fun addBookmark(){

    }
    private fun searchCourseOnMap(){

    }
}