package com.aurimteam.justhobby.user.course_info.course_reviews

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_card_review.view.*
import android.support.v4.graphics.drawable.DrawableCompat
import com.aurimteam.justhobby.R


class CourseReviewsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        last: Boolean,
        name: String,
        rating: Float,
        review: String
    ) {
        itemView.courseReviewName.text = name
        itemView.courseReviewRating.rating = rating
        val stars = itemView.courseReviewRating.progressDrawable
        when (rating) {
            1f -> DrawableCompat.setTint(stars, itemView.context.resources.getColor(R.color.ratingOne))
            2f -> DrawableCompat.setTint(stars, itemView.context.resources.getColor(R.color.ratingTwo))
            3f -> DrawableCompat.setTint(stars, itemView.context.resources.getColor(R.color.ratingThree))
            4f -> DrawableCompat.setTint(stars, itemView.context.resources.getColor(R.color.ratingFour))
            5f -> DrawableCompat.setTint(stars, itemView.context.resources.getColor(R.color.ratingFive))
            else -> DrawableCompat.setTint(stars, itemView.context.resources.getColor(R.color.grayDarkMiddle))
        }
        itemView.courseReviewUserReview.text = review
        if(last) {
            itemView.courseReviewLineBottom.visibility = View.INVISIBLE
        }
    }
}