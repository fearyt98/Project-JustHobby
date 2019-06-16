package com.aurimteam.justhobby.course

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.card_course.view.*

class CourseHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        rating: String,
        titleCourse: String,
        titleCompany: String,
        address: String,
        length: Int?,
        status: Boolean,
        typePayment: Int,
        price: Int,
        sex: List<Int>,
        ageMax: Int,
        ageMin: Int,
        user: Boolean?
    ) {
        if(rating != "-") {
            val ratingDouble = rating.toDouble()
            when {
                ratingDouble < 1.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating1)
                ratingDouble >= 1.5 && ratingDouble < 2.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating2)
                ratingDouble >= 2.5 && ratingDouble < 3.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating3)
                ratingDouble >= 3.5 && ratingDouble < 4.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating4)
                ratingDouble >= 4.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating5)
            }
        } else {
            itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating_none)
        }
        itemView.cardCourseRating.text = rating
        itemView.cardCourseTitle.text = titleCourse
        itemView.cardCourseCompany.text = titleCompany
        itemView.cardCourseAddress.text = address
        if(length != null) {
            itemView.cardCourseMetres.text =
                String.format(itemView.context.resources.getString(R.string.length_meters), length)
        } else {
            itemView.cardCourseAddressSep.visibility = View.GONE
            itemView.cardCourseMetres.visibility = View.GONE
        }

        itemView.cardCourseStatus.setImageResource(
            if (status) R.drawable.ic_success_24dp else R.drawable.ic_fail_24dp
        )

        itemView.cardCourseSex.visibility = View.GONE
        itemView.cardCourseSexMale.visibility = View.GONE
        itemView.cardCourseSexFemale.visibility = View.GONE

        for (i in sex) {
            when (i) {
                0 -> itemView.cardCourseSex.visibility = View.VISIBLE
                1 -> itemView.cardCourseSexMale.visibility = View.VISIBLE
                2 -> itemView.cardCourseSexFemale.visibility = View.VISIBLE
            }
        }
        itemView.cardCoursePrice.text =
            String.format(
                itemView.context.resources.getString(
                    when (typePayment) {
                        1 -> R.string.payment_month
                        2 -> R.string.payment_course
                        else -> R.string.payment_one
                    }
                ),
                price
            )
        itemView.cardCourseAgeRange.text = "$ageMin - $ageMax"

        changeColorBtnBookmark(user)
    }

    fun changeColorBtnBookmark(user: Boolean?) {
        if(user != null && user)
            itemView.cardCourseBtnBookmark.setColorFilter(
                ContextCompat.getColor(itemView.context, R.color.red)
            )
        else
            itemView.cardCourseBtnBookmark.setColorFilter(
                ContextCompat.getColor(itemView.context, R.color.gray)
            )
    }
}