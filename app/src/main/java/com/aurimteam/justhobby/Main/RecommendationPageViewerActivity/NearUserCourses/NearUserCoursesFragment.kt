package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.NearUserCourses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces.INearCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.activity_near_user_courses_fragment.*

class NearUserCoursesFragment : Fragment(), INearCoursesView {

    private val presenter = NearUserCoursesPresenter(this, NearUserCoursesModel())
    private var adapter = NearUserCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_near_user_courses_fragment, container, false)
    }

    override fun showNearUserCourses(courses: List<CourseResponse>) {
        adapter.onDataChange(courses)
    }

    override fun onStart() {
        super.onStart()
        presenter.getNearCourses()
        nearPlacesRecyclerView.layoutManager = LinearLayoutManager(context)
        nearPlacesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
