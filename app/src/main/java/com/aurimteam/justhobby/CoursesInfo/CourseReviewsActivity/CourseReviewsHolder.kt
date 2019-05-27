package com.aurimteam.justhobby.CoursesInfo.CourseReviewsActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_course_reviews.view.*

class CourseReviewsHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(
        id: Long,
        name: String,
        rating: Float,
        review: String
    ) {
        itemView.courseReviewName.text = name
        itemView.courseReviewRating.rating = rating
        itemView.courseReviewUserReview.text = review
    }
}