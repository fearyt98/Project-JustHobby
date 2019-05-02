package com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.PopularCourses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.Main.RecomPopularPageViewerActivity.FragmentsInterfaces.IPopularCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.activity_popular_places_fragment.*

class PopularCoursesFragment : Fragment(), IPopularCoursesView {

    private val presenter = PopularCoursesPresenter(this, PopularCoursesModel())
    private var adapter = PopularCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_popular_places_fragment, container, false)
    }

    override fun showPopularCourses(places: List<CourseResponse>) {
        //adapter.onDataChange()
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
