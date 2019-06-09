package com.aurimteam.justhobby.user.course_info.course_reviews

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.card_review.view.*
import android.support.v4.graphics.drawable.DrawableCompat
import com.aurimteam.justhobby.R
import java.text.SimpleDateFormat
import java.util.*

class CourseReviewsHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        last: Boolean,
        name: String,
        rating: Float,
        review: String,
        createdAt: Long
    ) {
        itemView.cardReviewName.text = name
        itemView.cardReviewRating.rating = rating
        val stars = itemView.cardReviewRating.progressDrawable
        when (rating) {
            1f -> DrawableCompat.setTint(stars, ContextCompat.getColor(itemView.context, R.color.ratingOne))
            2f -> DrawableCompat.setTint(stars, ContextCompat.getColor(itemView.context, R.color.ratingTwo))
            3f -> DrawableCompat.setTint(stars, ContextCompat.getColor(itemView.context, R.color.ratingThree))
            4f -> DrawableCompat.setTint(stars, ContextCompat.getColor(itemView.context, R.color.ratingFour))
            5f -> DrawableCompat.setTint(stars, ContextCompat.getColor(itemView.context, R.color.ratingFive))
            else -> DrawableCompat.setTint(stars, ContextCompat.getColor(itemView.context, R.color.grayDarkMiddle))
        }
        itemView.cardReviewReview.text = review
        if(last) {
            itemView.cardReviewLineBottom.visibility = View.INVISIBLE
        }

        itemView.cardReviewDateTime.text =
            SimpleDateFormat("d MMMM в HH:mm", Locale.getDefault()).format(Date(createdAt * 1000))
    }
}