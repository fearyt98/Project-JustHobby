package com.aurimteam.justhobby.user.main.recommendation_page_viewer.near_user_courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.INearCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import kotlinx.android.synthetic.main.fragment_near_user_courses.*

class NearUserCoursesFragment : Fragment(), INearCoursesView {

    private val presenter = NearUserCoursesPresenter(this, NearUserCoursesModel())
    private var adapter = NearUserCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_near_user_courses, container, false)
        return view
    }

    override fun showNearUserCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        adapter.onDataChange(courses, included!!)
    }

    override fun onStart() {
        super.onStart()
        presenter.getNearCourses()
        nearCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        nearCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}
