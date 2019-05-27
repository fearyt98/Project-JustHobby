package com.aurimteam.justhobby.CoursesInfo.CourseReviewActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class CourseReviewFragment : Fragment(), ICourseReviewView {

    private val presenter = CourseReviewPresenter(this, CourseReviewModel())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_review, container, false)
        return view
    }

    override fun showReview() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
