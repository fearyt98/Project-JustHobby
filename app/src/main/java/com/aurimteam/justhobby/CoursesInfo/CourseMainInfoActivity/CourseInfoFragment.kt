package com.aurimteam.justhobby.CoursesInfo.CourseMainInfoActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.Response.GroupResponse
import kotlinx.android.synthetic.main.fragment_course_main.*

class CourseInfoFragment : Fragment(), ICourseInfoView {

    private val presenter = CourseInfoPresenter(this, CourseInfoModel())
    private val adapter = CourseInfoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_main, container, false)
        return view
    }

    override fun showCourseGroups(groups: List<GroupResponse>) {
        adapter.onDataChange(groups)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCourseGroups()
        groupsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        groupsRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
