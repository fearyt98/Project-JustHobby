package com.aurimteam.justhobby.user.main.recommendation_page_viewer.popular_courses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.user.main.recommendation_page_viewer.fragments_interfaces.IPopularCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CourseResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.IncludedResponse
import kotlinx.android.synthetic.main.fragment_popular_courses.*

class PopularCoursesFragment : Fragment(), IPopularCoursesView {

    private val presenter = PopularCoursesPresenter(this, PopularCoursesModel())
    private var adapter = PopularCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_courses, container, false)
    }

    override fun showPopularCourses(courses: List<CourseResponseR>, included: IncludedResponse?) {
        adapter.onDataChange(courses, included!!)
    }

    override fun onStart() {
        super.onStart()
        presenter.getPopularCourses()
        popularCoursesRecyclerView.layoutManager = LinearLayoutManager(context)
        popularCoursesRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
