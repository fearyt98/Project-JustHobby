package com.aurimteam.justhobby.user.course_info.сourse_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.aurimteam.justhobby.user.course_info.course_groups.CourseGroupsFragment
import com.aurimteam.justhobby.R
import com.aurimteam.justhobby.response.CompanyResponse
import com.aurimteam.justhobby.response.CourseResponseR
import com.aurimteam.justhobby.response.GroupResponse
import kotlinx.android.synthetic.main.fragment_course_info.*

class CourseInfoFragment() : Fragment(), ICourseInfoView {

    private val presenter = CourseInfoPresenter(this, CourseInfoModel())
    private val adapter = CourseInfoAdapter()
    private var course: CourseResponseR? = null
    private var company: CompanyResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course_info, container, false)
        view.findViewById<ImageButton>(R.id.courseInfoBtnBack).setOnClickListener { backToRecommendedFragment() }
        view.findViewById<TextView>(R.id.courseInfoShowAllCourses).setOnClickListener { allCompanyCoursesFragment() }
        val bundle = arguments
        return view
    }

    override fun showCourseGroups(groups: List<GroupResponse>) {
        adapter.onDataChange(groups)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCourseGroups()
        courseInfoGroupsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        courseInfoGroupsRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun backToRecommendedFragment() {
        fragmentManager?.popBackStack()
    }

    private fun allCompanyCoursesFragment() {
        fragmentManager!!
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainNavContainerFragment, CourseGroupsFragment())
            .commit()
    }

}
