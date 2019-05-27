package com.aurimteam.justhobby.CoursesInfo.CourseReviewsActivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseReviewsResponse

class CourseReviewsAdapter : RecyclerView.Adapter<CourseReviewsHolder>() {

    private val courseReviewsList: MutableList<CourseReviewsResponse> = mutableListOf()

    override fun getItemCount(): Int = courseReviewsList.size
    override fun onBindViewHolder(holder: CourseReviewsHolder, position: Int) {
        holder.bind(
            courseReviewsList[position].id,
            courseReviewsList[position].name,
            courseReviewsList[position].rating,
            courseReviewsList[position].review
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseReviewsHolder =
        CourseReviewsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_card_course_reviews,
                parent,
                false
            )
        )

    fun onDataChange(courseReviews: List<CourseReviewsResponse>) {
        this.courseReviewsList.clear()
        this.courseReviewsList.addAll(courseReviews)
        notifyDataSetChanged()
    }
}