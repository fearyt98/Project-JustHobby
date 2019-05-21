package com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.GroupResponse
import kotlinx.android.synthetic.main.fragment_course_main.*

class CourseInfoFragment : Fragment(), ICourseInfoView {

    private val presenter = CourseInfoPresenter(this, CourseInfoModel())
    private val adapter = CourseInfoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_main, container, false)
        view.findViewById<ImageButton>(R.id.backArrowCourseInfo).setOnClickListener { backToRecommendedFragment() }
        return view
    }

    override fun showCourseGroups(groups: List<GroupResponse>) {
        adapter.onDataChange(groups)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCourseGroups()
        groupsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        groupsRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
    private fun backToRecommendedFragment() {
        fragmentManager?.popBackStack()
    }

}
