package com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.PopularCourses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.Main.RecommendationPageViewerActivity.FragmentsInterfaces.IPopularCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.activity_popular_courses_fragment.*

class PopularCoursesFragment : Fragment(), IPopularCoursesView {

    private val presenter = PopularCoursesPresenter(this, PopularCoursesModel())
    private var adapter = PopularCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_popular_courses_fragment, container, false)
    }

    override fun showPopularCourses(courses: List<CourseResponse>) {
        adapter.onDataChange(courses)
    }

    override fun onStart() {
        super.onStart()
        presenter.getPopularCourses()
        PopularCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        PopularCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
