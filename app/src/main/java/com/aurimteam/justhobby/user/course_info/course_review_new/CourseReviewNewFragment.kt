package com.aurimteam.justhobby.user.course_info.course_review_new

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R

class CourseReviewNewFragment : Fragment(), ICourseReviewNewView {

    private val presenter = CourseReviewNewPresenter(this, CourseReviewNewModel())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_review_new, container, false)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
