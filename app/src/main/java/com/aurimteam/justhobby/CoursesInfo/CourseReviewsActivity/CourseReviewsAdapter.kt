package com.aurimteam.justhobby.CoursesInfo.CourseReviewsActivity

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aurimteam.justhobby.CoursesInfo.CourseReviewActivity.CourseReviewFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseReviewsResponse
import kotlinx.android.synthetic.main.fragment_card_review.view.*

class CourseReviewsAdapter : RecyclerView.Adapter<CourseReviewsHolder>() {

    private val courseReviewsList: MutableList<CourseReviewsResponse> = mutableListOf()

    override fun getItemCount(): Int = courseReviewsList.size
    override fun onBindViewHolder(holder: CourseReviewsHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.bind(
            courseReviewsList[position].id,
            position == itemCount - 1,
            courseReviewsList[position].name,
            courseReviewsList[position].rating,
            courseReviewsList[position].review
        )

        holder.itemView.courseReview.setOnClickListener {
            detailReviews(manager)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseReviewsHolder =
        CourseReviewsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_card_review,
                parent,
                false
            )
        )

    fun onDataChange(courseReviews: List<CourseReviewsResponse>) {
        this.courseReviewsList.clear()
        this.courseReviewsList.addAll(courseReviews)
        notifyDataSetChanged()
    }

    private fun detailReviews(fm: FragmentManager) {
        fm.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseReviewFragment())
            .commit()
    }
}