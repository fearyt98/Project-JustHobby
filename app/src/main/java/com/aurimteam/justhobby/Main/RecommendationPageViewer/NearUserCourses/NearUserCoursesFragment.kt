package com.aurimteam.justhobby.Main.RecommendationPageViewer.NearUserCourses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity.CourseInfoFragment
import com.aurimteam.justhobby.Main.RecommendationPageViewer.FragmentsInterfaces.INearCoursesView
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.CourseResponse
import kotlinx.android.synthetic.main.fragment_near_user_courses.*

class NearUserCoursesFragment : Fragment(), INearCoursesView {

    private val presenter = NearUserCoursesPresenter(this, NearUserCoursesModel())
    private var adapter = NearUserCoursesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_near_user_courses, container, false)
        return view
    }

    override fun showNearUserCourses(courses: List<CourseResponse>) {
        adapter.onDataChange(courses)
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
