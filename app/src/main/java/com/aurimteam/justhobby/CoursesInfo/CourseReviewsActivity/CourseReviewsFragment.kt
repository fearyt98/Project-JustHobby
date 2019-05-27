package com.aurimteam.justhobby.CoursesInfo.CourseReviewsActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseReviewsResponse
import kotlinx.android.synthetic.main.fragment_course_reviews.*

class CourseReviewsFragment : Fragment(), ICourseReviewsView {

    private val presenter = CourseReviewsPresenter(this, CourseReviewsModel())
    private val adapter = CourseReviewsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_reviews, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getCourseReviews()
        courseReviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        courseReviewsRecyclerView.adapter = adapter
    }

    override fun showCourseReviews(courseReviews: List<CourseReviewsResponse>) {
        adapter.onDataChange(courseReviews)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}