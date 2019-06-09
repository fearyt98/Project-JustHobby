package com.aurimteam.justhobby.user.course_info.course_reviews

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.user.course_info.course_review.CourseReviewFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.ReviewResponse
import kotlinx.android.synthetic.main.card_review.view.*

class CourseReviewsAdapter : RecyclerView.Adapter<CourseReviewsHolder>() {

    private val courseReviewsList: MutableList<ReviewResponse> = mutableListOf()
    private var courseName: String = ""
    private var companyName: String = ""

    override fun getItemCount(): Int = courseReviewsList.size
    override fun onBindViewHolder(holder: CourseReviewsHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.bind(
            position == itemCount - 1,
            courseReviewsList[position].attributes.user_name,
            courseReviewsList[position].attributes.rating.toFloat(),
            courseReviewsList[position].attributes.review,
            courseReviewsList[position].attributes.created_at
        )

        holder.itemView.cardReview.setOnClickListener {
            detailReviews(manager, courseReviewsList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseReviewsHolder =
        CourseReviewsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_review,
                parent,
                false
            )
        )

    fun onDataChange(courseReviews: List<ReviewResponse>, courseName: String, companyName: String) {
        courseReviewsList.clear()
        courseReviewsList.addAll(courseReviews)
        this.companyName = companyName
        this.courseName = courseName
        notifyDataSetChanged()
    }

    private fun detailReviews(fm: FragmentManager, review: ReviewResponse) {
        val bundle = Bundle()
        bundle.putString("course_name", courseName)
        bundle.putString("company_name", companyName)
        bundle.putString("user_name", review.attributes.user_name)
        bundle.putString("review", review.attributes.review)
        bundle.putString("rating", review.attributes.rating.toString())
        bundle.putString("created_at", review.attributes.created_at.toString())

        val courseReviewFragment = CourseReviewFragment()
        courseReviewFragment.arguments = bundle

        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, courseReviewFragment)
            .commit()
    }
}