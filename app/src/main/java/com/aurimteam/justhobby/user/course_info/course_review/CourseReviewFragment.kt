package com.aurimteam.justhobby.user.course_info.course_review

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import kotlinx.android.synthetic.main.fragment_course_review.*
import java.text.SimpleDateFormat
import java.util.*

class CourseReviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course_review, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (arguments != null) {
            courseReviewTitle.text = arguments!!.get("course_name")!!.toString()
            courseReviewCompany.text = arguments!!.get("company_name")!!.toString()
            courseReviewName.text = arguments!!.get("user_name")!!.toString()
            val rating = arguments!!.get("rating")!!.toString().toFloat()
            courseReviewRating.rating = rating
            if (context != null) {
                val stars = courseReviewRating.progressDrawable
                when (rating) {
                    1f -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.ratingOne))
                    2f -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.ratingTwo))
                    3f -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.ratingThree))
                    4f -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.ratingFour))
                    5f -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.ratingFive))
                    else -> DrawableCompat.setTint(stars, ContextCompat.getColor(context!!, R.color.grayDarkMiddle))
                }
            }
            courseReviewReview.text = arguments!!.get("review")!!.toString()
            val createdAt = arguments!!.get("created_at")!!.toString().toLong()

            courseReviewDateTime.text =
                SimpleDateFormat("d MMMM в HH:mm", Locale.getDefault()).format(Date(createdAt * 1000))
        }
        courseReviewBtnBack.setOnClickListener { back() }
    }

    private fun back() {
        fragmentManager?.popBackStack()
    }
}
