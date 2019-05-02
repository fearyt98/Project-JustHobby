package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularCourses

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse

class PopularCoursesAdapter : RecyclerView.Adapter<PopularCoursesHolder>() {
    private val coursesList: MutableList<CourseResponse> = mutableListOf()

    override fun getItemCount(): Int = coursesList.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PopularCoursesHolder =
        PopularCoursesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_card_course,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PopularCoursesHolder, position: Int) {
        holder.bind(
            coursesList[position].id,
            coursesList[position].title,
            coursesList[position].description,
            coursesList[position].address,
            coursesList[position].company,
            coursesList[position].category
        )
    }

    fun onDataChange(courses: List<CourseResponse>) {
        this.coursesList.clear()
        this.coursesList.addAll(courses)
        notifyDataSetChanged()
    }
}