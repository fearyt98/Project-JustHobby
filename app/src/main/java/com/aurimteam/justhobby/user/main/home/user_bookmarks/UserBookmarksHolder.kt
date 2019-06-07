package com.aurimteam.justhobby.user.main.home.user_bookmarks

import android.support.v7.widget.RecyclerView
import android.view.View
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseAttrResponse
import com.aurimteam.justhobby.response.IdentifierResponse
import kotlinx.android.synthetic.main.card_course.view.*
import kotlinx.android.synthetic.main.card_event.view.*

class UserBookmarksHolder(view: View) : RecyclerView.ViewHolder(view) {
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
        ageMin: Int
    ) {
        val ratingInt = rating.toDouble()
        when {
            ratingInt in 0.0..1.0 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating1)
            ratingInt > 1 && ratingInt < 2 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating2)
            ratingInt >= 2 && ratingInt < 3 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating3)
            ratingInt >= 3 && ratingInt < 4.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating4)
            ratingInt >= 4.5 -> itemView.cardCourseRatingBg.setBackgroundResource(R.drawable.card_rating5)
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
    }
}